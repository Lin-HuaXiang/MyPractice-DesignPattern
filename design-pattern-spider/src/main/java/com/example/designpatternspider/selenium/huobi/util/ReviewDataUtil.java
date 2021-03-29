package com.example.designpatternspider.selenium.huobi.util;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.DateUtils;
import com.example.designpatternspider.selenium.huobi.api.HuobiOpenSpider;
import com.example.designpatternspider.selenium.huobi.po.Data;
import com.example.designpatternspider.selenium.huobi.po.HuoBiKline;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;
import com.example.designpatternspider.selenium.huobi.util.excel.ReviewExportListener;
import com.example.designpatternspider.selenium.util.MacdIndicator;
import com.example.designpatternspider.selenium.util.SpiderUtil;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewDataUtil {

    /**
     * The computer user root directory
     */
    public static final String USER_HOME = System.getProperty("user.home");

    /**
     * FIL FUTRUE DATA
     */
    public static final String FIL_FUTURE_DATA = USER_HOME + "\\huobi\\fil\\future\\data\\";

    public static final String EXCEL_NAME = "fil-%s.xlsx";

    public static void main(String[] args) throws Exception {
        String period = "1day";
        String fileName = String.format(EXCEL_NAME, period);
        File dayData = new File(FIL_FUTURE_DATA + fileName);
        if (!dayData.exists()) {
            importHistoricalData(period);
        }
        List<ReviewExport> listData = getLocalData(period);
        calcData(listData, period);
    }

    public static List<ReviewExport> getLocalData(String period) {

        String fileName = String.format(EXCEL_NAME, period);
        List<ReviewExport> listData = new ArrayList<>();

        File file = new File(FIL_FUTURE_DATA + fileName);
        ReviewExportListener easyExcelListener = new ReviewExportListener();
        EasyExcelFactory.read(file.getAbsolutePath(), ReviewExport.class, easyExcelListener).sheet().doRead();
        listData.addAll(easyExcelListener.getList());
        return listData;
    }

    public static void calcData(List<ReviewExport> listData, String period) {
        Boolean bool = false;
        ReviewExport re;
        for (int i = 0; i < listData.size() - 1; i++) {
            re = listData.get(i);
            if (ObjectUtils.isEmpty(listData.get(i).getMacd())) {
                List<ReviewExport> subList = listData.subList(0, i + 1);
                List<BigDecimal> tempList = subList.stream().map(u -> u.getPrice()).collect(Collectors.toList());
                HashMap<String, BigDecimal> macd = MacdIndicator.getMACD(tempList, 12, 26, 9);
                re.setDif(macd.get("DIF"));
                re.setDea(macd.get("DEA"));
                re.setMacd(macd.get("MACD"));
                bool |= true;
            }
        }
        if (Boolean.TRUE.equals(bool)) {
            String fileName = String.format(EXCEL_NAME, period);
            ExcelWriter build = EasyExcelFactory.write(FIL_FUTURE_DATA + fileName, ReviewExport.class).build();
            build.write(listData, EasyExcelFactory.writerSheet(0, "sheet0").build());
            build.finish();
        }
    }

    public static void calcLatestMACD(String period) throws Exception {

        String fileName = String.format(EXCEL_NAME, period);
        List<ReviewExport> listData = new ArrayList<>();

        File file = new File(FIL_FUTURE_DATA + fileName);
        ReviewExportListener easyExcelListener = new ReviewExportListener();
        EasyExcelFactory.read(file.getAbsolutePath(), ReviewExport.class, easyExcelListener).sheet().doRead();
        listData.addAll(easyExcelListener.getList());

        List<BigDecimal> list = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            list.add(listData.get(i).getPrice());
        }

        HashMap<String, BigDecimal> macdBigDecimal = MacdIndicator.getMACD(list, 12, 26, 9);
        log.info("{}", macdBigDecimal);
    }

    public static void importHistoricalData(String period) throws Exception {
        WebDriver driver = null;
        try {
            String fileName = String.format(EXCEL_NAME, period);
            driver = HuobiOpenSpider.driverBuilderChromeHeadLess();
            Actions action = new Actions(driver);
            WebDriverWait driverWait = new WebDriverWait(driver, 30);

            SpiderUtil.requestData(HuobiOpenSpider.API_URL, driver);

            Thread.sleep(1000);
            // url
            HuobiOpenSpider.getMain(driver);
            // fil usdt
            HuobiOpenSpider.inputSymbol(driver, action, "filusdt");
            // 1min
            HuobiOpenSpider.inputPeriod(driver, action, period);
            // 72
            HuobiOpenSpider.inputSize(driver, action, "2000");

            HuobiOpenSpider.sendRequest(driver, action);
            HuoBiKline kline = HuobiOpenSpider.getData(driver);

            long ts = kline.getTs();
            Date date = new Date(ts);

            List<Data> dataList = kline.getData();

            Calendar c = Calendar.getInstance();
            c.setTime(date);

            List<ReviewExport> reviewList = new ArrayList<>();

            for (int i = 0; i < dataList.size(); i++) {
                Data data = dataList.get(i);
                data.setTime(DateUtils.format(c.getTime(), DateUtils.DATE_FORMAT_10));
                c.add(Calendar.DATE, -1);
            }

            for (int i = dataList.size() - 1; i >= 0; i--) {
                Data data = dataList.get(i);
                ReviewExport reviewExport = new ReviewExport();
                reviewExport.setPrice(data.getClose());
                reviewExport.setTime(data.getTime());
                if (dataList.size() - 1 == i) {
                    reviewExport.setMacd(BigDecimal.ZERO);
                    reviewExport.setDea(BigDecimal.ZERO);
                    reviewExport.setDif(BigDecimal.ZERO);
                }
                reviewList.add(reviewExport);
            }

            File doc = new File(FIL_FUTURE_DATA);
            if (!doc.exists()) {
                doc.mkdirs();
            }

            ExcelWriter build = EasyExcelFactory.write(FIL_FUTURE_DATA + fileName, ReviewExport.class).build();
            build.write(reviewList, EasyExcelFactory.writerSheet(0, "sheet0").build());
            build.finish();
        } catch (Exception e) {
            log.error("", e);
            SpiderUtil.closeBrowser(driver);
        }

    }

}
