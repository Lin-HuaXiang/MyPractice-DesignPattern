package com.example.designpatternspider.selenium.huobi.mock;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.designpatternspider.selenium.huobi.po.Signal;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewDataMockRsi extends ReviewDataMockIndicator {

    private BigDecimal lastRsi9;
    private BigDecimal latRsi12;
    private BigDecimal latRsi14;
    private BigDecimal lastRsi72;
    private BigDecimal lastPrice;


    public Signal calcMock(ReviewExport reviewExport) {
        // calculate rsi 9 12 72 14 value
        BigDecimal price = reviewExport.getPrice();
        BigDecimal calcRsi9 = reviewExport.getCalcRsi9();
        BigDecimal calcRsi12 = reviewExport.getCalcRsi12();
        BigDecimal calcRsi14 = reviewExport.getCalcRsi14();
        BigDecimal calcRsi72 = reviewExport.getCalcRsi72();

        if (ObjectUtils.isEmpty(calcRsi9) || ObjectUtils.isEmpty(calcRsi12) || ObjectUtils.isEmpty(calcRsi14)
                || ObjectUtils.isEmpty(calcRsi72)) {
            return new Signal();
        }

        // Initialize the last price on the first call
        if (ObjectUtils.isEmpty(lastPrice)) {
            lastPrice = price;
            lastRsi9 = calcRsi9;
            latRsi12 = calcRsi12;
            latRsi14 = calcRsi14;
            lastRsi72 = calcRsi72;
        }

        if (lastRsi9.compareTo(lastRsi72) < 0 && calcRsi9.compareTo(calcRsi72) > 0
                && calcRsi12.compareTo(calcRsi72) > 0) {
            // log.info("rsi up cross");
            signalOpenLong = true;
            signalCloseShort = true;
        }

        if (lastRsi9.compareTo(lastRsi72) > 0 && calcRsi9.compareTo(calcRsi72) < 0
                && calcRsi12.compareTo(calcRsi72) < 0) {
            // log.info("rsi down cross");
            signalOpenShort = true;
            signalCloseLong = true;
        }

        // The heat is too high, need to adapt to the macd mac line drop
        if (calcRsi9.divide(calcRsi72, 2, RoundingMode.HALF_DOWN).compareTo(BigDecimal.valueOf(1.45)) >= 0) {
            // log.info("rsi touch highest");
            signalOpenShort = true;
            signalCloseLong = true;
        }

        // The heat is too high, need to adjust to MACD line drop
        if (calcRsi9.divide(calcRsi72, 2, RoundingMode.HALF_DOWN).compareTo(BigDecimal.valueOf(0.55)) <= 0) {
            // log.info("rsi touch lowest");
            signalOpenLong = true;
            signalCloseShort = true;
        }

        // reverse
        log.info("P{}LRN{}RN{}LRT{}RT{}LRS{}RS{}", price, lastRsi9, calcRsi9, latRsi12, calcRsi12, lastRsi72, calcRsi72);

        return new Signal(signalOpenLong, signalOpenShort, signalCloseLong, signalCloseShort);

    }

}