package com.example.designpatternspider;

import java.io.File;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.fastjson.JSON;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMock;
import com.example.designpatternspider.selenium.huobi.po.Data;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;
import com.example.designpatternspider.selenium.huobi.util.ReviewDataUtil;
import com.example.designpatternspider.selenium.huobi.util.excel.ReviewExportListener;
import com.example.designpatternspider.selenium.huobi.wss.event.MarketKLineReqResponse;
import com.example.designpatternspider.selenium.huobi.wss.handle.WssMarketReqHandle;
import com.example.designpatternspider.selenium.huobi.wss.request.KLineSubRequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class WssReviewDataTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String URL = "wss://www.btcgateway.pro/ws";

    private String FILENAME = "market.BTC_CW.kline.1day";

    private static String fromDateString = "2021-2-1 00:00:00";
    private static String toDateString = "2021-4-15 10:00:00";

    private static Map<Long, ReviewExport> map = new ConcurrentHashMap<>();


    @BeforeEach
    public void before() {
        
        File file = new File(ReviewDataUtil.FIL_FUTURE_DATA);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (int i = 0; i < listFiles.length; i++) {
            File file2 = listFiles[i];
            String name = file2.getName();
            if (name.contains("all") && name.contains(FILENAME)) {
                List<ReviewExport> listData = new ArrayList<>();
                ReviewExportListener easyExcelListener = new ReviewExportListener();
                EasyExcelFactory.read(file2.getAbsolutePath(), ReviewExport.class, easyExcelListener).sheet().doRead();
                listData.addAll(easyExcelListener.getList());
                for (ReviewExport reviewExport : listData) {
                    map.put(reviewExport.getId(), reviewExport);
                }
            }
        }
    }

    @AfterEach
    public void after() throws InterruptedException {
        Thread.sleep(5000);
        List<ReviewExport> list = new ArrayList<>(map.values());
        Boolean bool = false;
        Collections.sort(list, (v1, v2) -> v1.getId().compareTo(v2.getId()));
        ReviewDataUtil.calcRSI(list, bool);
        ExcelWriter build = EasyExcelFactory
                .write(ReviewDataUtil.FIL_FUTURE_DATA + FILENAME + "-all.xlsx", ReviewExport.class).build();
        build.write(list, EasyExcelFactory.writerSheet(0, "sheet0").build());
        build.finish();
    }

    @Test
    public void test1() throws URISyntaxException, InterruptedException {

        WssMarketReqHandle wssMarketReqHandle = new WssMarketReqHandle(URL, response -> {
            // logger.info("请求 KLine 数据用户收到的原始数据:{}", response);
            MarketKLineReqResponse marketKLineReqResponse = JSON.parseObject(response, MarketKLineReqResponse.class);
            logger.info("数据为:{}", JSON.toJSON(marketKLineReqResponse));
            List<MarketKLineReqResponse.DataBean> data = marketKLineReqResponse.getData();
            List<ReviewExport> list = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                if (map.containsKey(data.get(i).getId())) {
                    continue;
                }
                ReviewExport reviewExport = new ReviewExport();
                MarketKLineReqResponse.DataBean dataBean = data.get(i);
                BeanUtils.copyProperties(dataBean, reviewExport);
                reviewExport.setTime(DateUtils.format(new Date(dataBean.getId() * 1000), DateUtils.DATE_FORMAT_19));
                reviewExport.setPrice(dataBean.getClose());
                list.add(reviewExport);
                map.put(reviewExport.getId(), reviewExport);
            }
        });

        // while (true) {
            try {
                Date fromDate = DateUtils.parseDate(fromDateString, "yyyy-MM-dd HH:mm:ss");
                Date toDate = DateUtils.parseDate(toDateString, "yyyy-MM-dd HH:mm:ss");
                KLineSubRequest kLineSubRequest = KLineSubRequest.builder().req(FILENAME)
                        .from(fromDate.getTime() / 1000).to(toDate.getTime() / 1000).build();
                wssMarketReqHandle.doReq(JSON.toJSONString(kLineSubRequest));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Thread.sleep(1000);
            }
        // }
    }

}
