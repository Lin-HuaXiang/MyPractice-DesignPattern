package com.example.designpatternspider;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMock;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMockMacd;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMockRsi;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;
import com.example.designpatternspider.selenium.huobi.util.ReviewDataUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ReviewDataMockTests {

        static String period = "4hour";
        static String currency = "btcusdt";
        static String[] periods = { "1min", "5min", "15min", "60min", "4hour", "1day" };

    @Test
    public void testLoadData() throws Exception {
        for (String period : periods) {
            try {
                String fileName = String.format(ReviewDataUtil.EXCEL_NAME, currency, period);
                File dayData = new File(ReviewDataUtil.FIL_FUTURE_DATA + fileName);
                if (!dayData.exists()) {
                    ReviewDataUtil.importHistoricalData(currency, period);
                }
                List<ReviewExport> listData = ReviewDataUtil.getLocalData(currency, period);
                ReviewDataUtil.calcData(listData, currency, period);
            } catch (Exception e) {
                log.error("", e);
            }
        }
        assertTrue(true);
    }



    @Test
    public void testPrintData() throws Exception {
        List<ReviewExport> listData = ReviewDataUtil.getLocalData(currency, period);
        log.info("{}", listData.subList(listData.size() - 10, listData.size()));
        assertTrue(true);
    }

    @Test
    public void testCalcData() throws Exception {
        List<ReviewExport> listData = ReviewDataUtil.getLocalData(currency, period);
        // // shock market
        // listData = listData.subList(listData.size() - 150, listData.size() - 50);
        // // shock
        // listData = listData.subList(listData.size() - 100, listData.size() - 50);
        // unilateral market long
        listData = listData.subList(listData.size() - 50, listData.size());
        // ReviewDataMock reviewDataMock = new ReviewDataMock(BigDecimal.valueOf(1), new ReviewDataMockMacd(), new ReviewDataMockRsi());
        // ReviewDataMock reviewDataMock = new ReviewDataMock(BigDecimal.valueOf(1), new ReviewDataMockMacd());
        ReviewDataMock reviewDataMock = new ReviewDataMock(BigDecimal.valueOf(1), new ReviewDataMockRsi());
        reviewDataMock.buildRepository(BigDecimal.valueOf(1));
        reviewDataMock.printResultMock(listData);
        
        assertTrue(true);
    }

}