package com.example.designpatternspider;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.example.designpatternspider.selenium.huobi.market.UnilateralMarket;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMock;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMockMacd;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMockRsi;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMockRsi01;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMockRsiSub;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;
import com.example.designpatternspider.selenium.huobi.util.MergeDataUtil;
import com.example.designpatternspider.selenium.huobi.util.ReviewDataUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ReviewDataMockTests {

        static String period = "60min";
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
    public void testCalcData() throws Exception {
        List<ReviewExport> listData = ReviewDataUtil.getLocalData(currency, period);
        // // shock market
        // listData = listData.subList(listData.size() - 150, listData.size() - 50);
        // // shock
        // listData = listData.subList(listData.size() - 100, listData.size() - 50);
        // unilateral market long
        UnilateralMarket unilateralMarket = new UnilateralMarket();
        unilateralMarket.load4HourData(currency);
        Map<String, String> marketStatusMap = unilateralMarket.getMarketStatusMap();
        listData = listData.subList(listData.size() - 50, listData.size());
        MergeDataUtil.mergeLowerData(listData, currency, period);
        // ReviewDataMock reviewDataMock = new ReviewDataMock(BigDecimal.valueOf(5), new ReviewDataMockMacd(), new ReviewDataMockRsi());
        // ReviewDataMock reviewDataMock = new ReviewDataMock(BigDecimal.valueOf(5), new ReviewDataMockMacd());
        ReviewDataMock reviewDataMock = new ReviewDataMock(BigDecimal.valueOf(5), new ReviewDataMockRsiSub());
        reviewDataMock.buildRepository(BigDecimal.valueOf(3));
        reviewDataMock.printResultMock(marketStatusMap, listData);
        // 4 hour macd, macd > 0 only open long, macd < 0 only open short.
        // unilateral market, rsi indicator highest/lowest inoperative. how to judge unilateral market ?
        // update excel data to calc 4 hour macd indicator, use for judge market status
        // different market use for different indicator
        // according to time point price, make price section to replace rsi highest and lowest.
        // level signal, judge open close signal.  e.g. macd three continual signal more than rsi highest and lowest.
        // multi indicator version, Identify the pros and cons of different versions
        // Fast slow pointer, stop closer than 20 per cent, merge lower level data.
        assertTrue(true);
    }
}
