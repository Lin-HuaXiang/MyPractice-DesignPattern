package com.example.designpatternspider.selenium.huobi.mock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.example.designpatternspider.selenium.huobi.po.Signal;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewDataMockRsiSub extends ReviewDataMockIndicator {

    private BigDecimal lastRsi9;
    private BigDecimal latRsi12;
    private BigDecimal latRsi14;
    private BigDecimal lastRsi72;
    private BigDecimal lastPrice;

    private Queue<String> touchQueue = new ArrayBlockingQueue<>(2);

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

        if (
            (lastRsi9.compareTo(lastRsi72) <= 0 && calcRsi9.compareTo(calcRsi72) > 0)
                // (latRsi12.compareTo(lastRsi72) <= 0 && calcRsi12.compareTo(calcRsi72) > 0)) {
                ) {
            // log.info("rsi up cross");
            signalOpenLong = true;
            signalCloseShort = true;
        } else if (
            (lastRsi9.compareTo(lastRsi72) >= 0 && calcRsi9.compareTo(calcRsi72) < 0)
                //  (latRsi12.compareTo(lastRsi72) >= 0 && calcRsi12.compareTo(calcRsi72) < 0)) {
                ) {
            // log.info("rsi down cross");
            signalOpenShort = true;
            signalCloseLong = true;
        }

        // // The heat is too high, need to adapt to the macd mac line drop
        if (calcRsi9.divide(calcRsi72, 2, RoundingMode.HALF_DOWN).compareTo(BigDecimal.valueOf(1.45)) >= 0) {
            log.info("rsi touch highest");

            boolean offer = touchQueue.offer("high");
            if (!offer) {
                touchQueue.poll();
                touchQueue.offer("high");
            }
            String e1 = touchQueue.peek();
            if (!"high".equals(e1) && touchQueue.size() == 2) {
                // Continuous highest, not open short
                signalOpenShort = true;
            } else if ("high".equals(e1) && touchQueue.size() == 2) {
                signalOpenLong = true;
                signalCloseShort = true;
                log.info("Continuous highest, open long, close short");
            }
        } else if (calcRsi9.divide(calcRsi72, 2, RoundingMode.HALF_DOWN).compareTo(BigDecimal.valueOf(0.55)) <= 0) {
            log.info("rsi touch lowest");
            // Continuous bottom, not open long
            boolean offer = touchQueue.offer("low");
            if (!offer) {
                touchQueue.poll();
                touchQueue.offer("low");
            }
            String e1 = touchQueue.peek();
            if (!"low".equals(e1) && touchQueue.size() == 2) {
                // Continuous lowest, not open short
                signalOpenLong = true;
            } else if ("low".equals(e1) && touchQueue.size() == 2) {
                signalOpenShort = true;
                signalCloseLong = true;
                log.info("Continuous lowest, open short, close long");
            }
        } else {
            boolean offer = touchQueue.offer("normal");
            if (!offer) {
                touchQueue.poll();
                touchQueue.offer("normal");
            }
        }

        // reverse
        log.info("[9]{}->{}[12]{}->{}[72]{}->{}", lastRsi9, calcRsi9,  latRsi12, calcRsi12, lastRsi72, calcRsi72);

        lastPrice = price;
        lastRsi9 = calcRsi9;
        latRsi12 = calcRsi12;
        latRsi14 = calcRsi14;
        lastRsi72 = calcRsi72;
        return new Signal(signalOpenLong, signalOpenShort, signalCloseLong, signalCloseShort);

    }

}
