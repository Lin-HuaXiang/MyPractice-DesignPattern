package com.example.designpatternspider;

import java.io.File;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.fastjson.JSON;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMock;
import com.example.designpatternspider.selenium.huobi.po.Data;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;
import com.example.designpatternspider.selenium.huobi.util.ReviewDataUtil;
import com.example.designpatternspider.selenium.huobi.wss.event.MarketKLineReqResponse;
import com.example.designpatternspider.selenium.huobi.wss.handle.WssMarketReqHandle;
import com.example.designpatternspider.selenium.huobi.wss.request.KLineSubRequest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class WssReviewDataTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String URL = "wss://www.btcgateway.pro/ws";//合约站行情请求以及订阅地址

    private String FILENAME = "market.BTC_CW.kline.60min";

    private static String fromDateString = "2021-4-3 00:00:00";
    private static String toDateString = "2021-4-5 00:00:00";

    @Test
    public void test1() throws URISyntaxException, InterruptedException {

        WssMarketReqHandle wssMarketReqHandle = new WssMarketReqHandle(URL, response -> {
//            logger.info("请求 KLine 数据用户收到的原始数据:{}", response);
            MarketKLineReqResponse marketKLineReqResponse = JSON.parseObject(response, MarketKLineReqResponse.class);
            logger.info("数据为:{}", JSON.toJSON(marketKLineReqResponse));
            List<MarketKLineReqResponse.DataBean> data = marketKLineReqResponse.getData();
            List<ReviewExport> list = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                if (i == 0) {
                    Date date = new Date(data.get(i).getId() * 1000);
                    Date parseDate;
                    try {
                        parseDate = DateUtils.parseDate(toDateString, DateUtils.DATE_FORMAT_19);
                    } catch (ParseException e) {
                        parseDate = new Date();
                    }
                    if (parseDate.compareTo(date) < 0) {
                        break;
                    }
                }
                ReviewExport reviewExport = new ReviewExport();
                MarketKLineReqResponse.DataBean dataBean = data.get(i);
                BeanUtils.copyProperties(dataBean, reviewExport);
                list.add(reviewExport);
            }
            if (!CollectionUtils.isEmpty(list)) {
                File doc = new File(ReviewDataUtil.FIL_FUTURE_DATA);
                if (!doc.exists()) {
                    doc.mkdirs();
                }
                ExcelWriter build = EasyExcelFactory.write(ReviewDataUtil.FIL_FUTURE_DATA + FILENAME + System.currentTimeMillis() + ".xlsx", ReviewExport.class).build();
                build.write(list, EasyExcelFactory.writerSheet(0, "sheet0").build());
                build.finish();
            }

        });

        while (true) {
            try {
                Date fromDate = DateUtils.parseDate(fromDateString, "yyyy-MM-dd HH:mm:ss");
                Date toDate = DateUtils.parseDate(toDateString, "yyyy-MM-dd HH:mm:ss");
                KLineSubRequest kLineSubRequest = KLineSubRequest.builder()
                        .req(FILENAME)
                        .from(fromDate.getTime() / 1000)
                        .to(toDate.getTime() / 1000)
                        .build();
                wssMarketReqHandle.doReq(JSON.toJSONString(kLineSubRequest));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Thread.sleep(1000);
            }

        }
    }
    
}
