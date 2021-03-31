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
import com.example.designpatternspider.selenium.util.RsiIndicator;
import com.example.designpatternspider.selenium.util.SpiderUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewDataUtil {

    /**
     * The computer user root directory
     */
    public static final String USER_HOME = System.getProperty("user.home");

    /**
     * FIL FUTURE DATA
     */
    public static final String FIL_FUTURE_DATA = USER_HOME + "\\huobi\\fil\\future\\data\\";

    public static final String EXCEL_NAME = "fil-%s.xlsx";

    public static void main(String[] args) throws Exception {
        // 1min, 5min, 15min, 30min, 60min, 4hour, 1day, 1mon, 1week, 1year
        String[] periods = { "1min", "5min", "15min", "60min", "4hour", "1day" };
        for (String period : periods) {
            try {
                String fileName = String.format(EXCEL_NAME, period);
                File dayData = new File(FIL_FUTURE_DATA + fileName);
                if (!dayData.exists()) {
                    importHistoricalData(period);
                }
                List<ReviewExport> listData = getLocalData(period);
                calcData(listData, period);
            } catch (Exception e) {
                log.error("", e);
            }
        }
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

        bool = calcMACD(listData, bool);

        bool = calcRSI(listData, bool);

        if (Boolean.TRUE.equals(bool)) {
            String fileName = String.format(EXCEL_NAME, period);
            ExcelWriter build = EasyExcelFactory.write(FIL_FUTURE_DATA + fileName, ReviewExport.class).build();
            build.write(listData, EasyExcelFactory.writerSheet(0, "sheet0").build());
            build.finish();
        }
    }

    private static Boolean calcMACD(List<ReviewExport> listData, Boolean bool) {
        ReviewExport re;
        // calculate macd
        for (int i = 0; i < listData.size(); i++) {
            re = listData.get(i);
            if (ObjectUtils.isEmpty(listData.get(i).getMacd())) {
                List<ReviewExport> subList = listData.subList(0, i + 1);
                List<BigDecimal> tempList = subList.stream().map(ReviewExport::getPrice).collect(Collectors.toList());
                HashMap<String, BigDecimal> macd = MacdIndicator.getMACD(tempList, 12, 26, 9);
                re.setDif(macd.get("DIF"));
                re.setDea(macd.get("DEA"));
                re.setMacd(macd.get("MACD"));
                bool |= true;
            }
        }
        return bool;
    }

    private static Boolean calcRSI(List<ReviewExport> listData, Boolean bool) {
        ReviewExport re;
        // calculate ris
        for (int i = listData.size() - 1; i >= 0; i--) {
            re = listData.get(i);
            if (i - 8 >= 0 && ObjectUtils.isEmpty(re.getCalcRsi9())) {
                List<ReviewExport> subList = listData.subList(i - 8, i + 1);
                List<Data> tempList = new ArrayList<>();
                for (ReviewExport reviewExport : subList) {
                    Data data = new Data();
                    BeanUtils.copyProperties(reviewExport, data);
                    tempList.add(data);
                }
                BigDecimal calcRsi9 = RsiIndicator.calcRsi(tempList);
                re.setCalcRsi9(calcRsi9);
                bool |= true;
            }
            if (i - 11 >= 0 && ObjectUtils.isEmpty(re.getCalcRsi12())) {
                List<ReviewExport> subList = listData.subList(i - 11, i + 1);
                List<Data> tempList = new ArrayList<>();
                for (ReviewExport reviewExport : subList) {
                    Data data = new Data();
                    BeanUtils.copyProperties(reviewExport, data);
                    tempList.add(data);
                }
                BigDecimal calcRsi12 = RsiIndicator.calcRsi(tempList);
                re.setCalcRsi12(calcRsi12);
                bool |= true;
            }
            if (i - 13 >= 0 && ObjectUtils.isEmpty(re.getCalcRsi14())) {
                List<ReviewExport> subList = listData.subList(i - 13, i + 1);
                List<Data> tempList = new ArrayList<>();
                for (ReviewExport reviewExport : subList) {
                    Data data = new Data();
                    BeanUtils.copyProperties(reviewExport, data);
                    tempList.add(data);
                }
                BigDecimal calcRsi14 = RsiIndicator.calcRsi(tempList);
                re.setCalcRsi14(calcRsi14);
                bool |= true;
            }
            if (i - 71 >= 0 && ObjectUtils.isEmpty(re.getCalcRsi72())) {
                List<ReviewExport> subList = listData.subList(i - 71, i + 1);
                List<Data> tempList = new ArrayList<>();
                for (ReviewExport reviewExport : subList) {
                    Data data = new Data();
                    BeanUtils.copyProperties(reviewExport, data);
                    tempList.add(data);
                }
                BigDecimal calcRsi72 = RsiIndicator.calcRsi(tempList);
                re.setCalcRsi72(calcRsi72);
                bool |= true;
            }
        }
        return bool;
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
            HuobiOpenSpider.inputSize(driver, action, "200");

            HuobiOpenSpider.sendRequest(driver, action);
            HuoBiKline kline = HuobiOpenSpider.getData(driver);

            long ts = kline.getTs();
            Date date = new Date(ts);

            List<Data> dataList = kline.getData();

            List<ReviewExport> reviewList = new ArrayList<>();

            calcPeriodTime(period, dataList, ts);

            for (int i = dataList.size() - 1; i >= 0; i--) {
                Data data = dataList.get(i);
                ReviewExport reviewExport = new ReviewExport();
                reviewExport.setPrice(data.getClose());
                reviewExport.setClose(data.getClose());
                reviewExport.setOpen(data.getOpen());
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
            Thread.sleep(5000);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            SpiderUtil.closeBrowser(driver);
        }
    }

    private static void calcPeriodTime(String period, List<Data> dataList, long ts) {
        int minute;
        int hour;
        Date date = new Date(ts);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.SECOND, 0);
        switch (period) {
        case "1min":
            for (int i = 0; i < dataList.size(); i++) {
                Data data = dataList.get(i);
                data.setTime(DateUtils.format(c.getTime(), DateUtils.DATE_FORMAT_19));
                c.add(Calendar.MINUTE, -1);
            }
            break;
        case "5min":
            minute = c.get(Calendar.MINUTE);
            c.add(Calendar.MINUTE, -(minute % 5));
            for (int i = 0; i < dataList.size(); i++) {
                Data data = dataList.get(i);
                data.setTime(DateUtils.format(c.getTime(), DateUtils.DATE_FORMAT_19));
                c.add(Calendar.MINUTE, -5);
            }
            break;
        case "15min":
            minute = c.get(Calendar.MINUTE);
            c.add(Calendar.MINUTE, -(minute % 15));
            for (int i = 0; i < dataList.size(); i++) {
                Data data = dataList.get(i);
                data.setTime(DateUtils.format(c.getTime(), DateUtils.DATE_FORMAT_19));
                c.add(Calendar.MINUTE, -15);
            }
            break;
        case "60min":
            c.set(Calendar.MINUTE, 0);
            for (int i = 0; i < dataList.size(); i++) {
                Data data = dataList.get(i);
                data.setTime(DateUtils.format(c.getTime(), DateUtils.DATE_FORMAT_19));
                c.add(Calendar.HOUR, -1);
            }
            break;
        case "4hour":
            c.set(Calendar.MINUTE, 0);
            hour = c.get(Calendar.HOUR);
            c.add(Calendar.HOUR, -(hour % 4));
            for (int i = 0; i < dataList.size(); i++) {
                Data data = dataList.get(i);
                data.setTime(DateUtils.format(c.getTime(), DateUtils.DATE_FORMAT_19));
                c.add(Calendar.HOUR, -4);
            }
            break;
        case "1day":
            for (int i = 0; i < dataList.size(); i++) {
                Data data = dataList.get(i);
                data.setTime(DateUtils.format(c.getTime(), DateUtils.DATE_FORMAT_10));
                c.add(Calendar.DATE, -1);
            }
            break;
        default:
            break;
        }
    }

}
