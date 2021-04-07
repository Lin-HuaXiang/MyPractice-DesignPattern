package com.example.designpatternspider.selenium.huobi.mock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import com.example.designpatternspider.selenium.huobi.po.Signal;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewDataMock {

    public ReviewDataMock(BigDecimal multiples, ReviewDataMockIndicator... reviewDataMockIndicators) {
        this.multiples = multiples;
        this.reviewDataMockIndicators = reviewDataMockIndicators;
    }

    ReviewDataMockIndicator[] reviewDataMockIndicators;

    public void buildRepository(BigDecimal decimal) {
        longRepository = decimal;
        shortRepository = decimal;
    }

    private BigDecimal longCount = BigDecimal.ZERO;
    private BigDecimal shortCount = BigDecimal.ZERO;
    private BigDecimal longRepository = BigDecimal.valueOf(100);
    private BigDecimal shortRepository = BigDecimal.valueOf(100);
    private BigDecimal sum = BigDecimal.valueOf(10000);
    private BigDecimal max = BigDecimal.valueOf(Integer.MIN_VALUE);
    private BigDecimal min = BigDecimal.valueOf(Integer.MAX_VALUE);
    private BigDecimal lastPrice;
    private BigDecimal multiples;

    public void printResultMock(Map<String, String> marketStatusMap, List<ReviewExport> reviewExports) {
        for (ReviewExport reviewExport : reviewExports) {
            Signal signal = new Signal();
            String time = reviewExport.getTime().replace("-", "").replace(" ", "").replace(":", "");
            log.info("T{}", time);
            for (ReviewDataMockIndicator reviewDataMockIndicator : reviewDataMockIndicators) {
                Signal temp = reviewDataMockIndicator.calcMock(reviewExport);
                // Signaling between different indicators
                signal.unite(temp);
                reviewDataMockIndicator.resetSignal();
            }
            Integer hour = Integer.valueOf(time.substring(time.length() - 6, time.length() - 4));
            String day =  time.substring(0, time.length() - 6);
            hour -= hour % 4;
            String key = day + (hour > 9 ? hour : "0" + hour);
            String status = marketStatusMap.get(key);
            if ("long".equals(status)) {
                signal.setSignalOpenShort(false);
            } else {
                signal.setSignalOpenLong(false);
            }
            getTotalEquity(reviewExport.getPrice());
            calcResultMock(signal);
        }
        log.info("MI{} MA{}", min, max);
    }

    private void calcResultMock(Signal signal) {
        if (Boolean.TRUE.equals(signal.getSignalOpenLong())) {
            openLong();
        }
        if (Boolean.TRUE.equals(signal.getSignalOpenShort())) {
            openShort();
        }
        if (Boolean.TRUE.equals(signal.getSignalCloseLong())) {
            closeLong();
        }
        if (Boolean.TRUE.equals(signal.getSignalCloseShort())) {
            closeShort();
        }
    }

    private void openLong() {
        BigDecimal use = longRepository.divide(BigDecimal.valueOf(2), 6, RoundingMode.HALF_DOWN);
        longRepository =  longRepository.subtract(use);
        longCount = longCount.add(use);
    }

    private void openShort() {
        BigDecimal use = shortRepository.divide(BigDecimal.valueOf(2), 6, RoundingMode.HALF_DOWN);
        shortRepository = shortRepository.subtract(use);
        shortCount = shortCount.add(use);
    }

    private void closeLong() {
        longRepository = longRepository.add(longCount);
        BigDecimal use = longCount;
        longCount = BigDecimal.ZERO;
    }

    private void closeShort() {
        shortRepository = shortRepository.add(shortCount);
        BigDecimal use = shortCount;
        shortCount = BigDecimal.ZERO;
    }

    private BigDecimal getTotalEquity(BigDecimal price) {
        if (ObjectUtils.isEmpty(lastPrice)) {
            lastPrice = price;
        }
        BigDecimal subtractPrice = price.subtract(lastPrice);
        BigDecimal newSum = sum.add(subtractPrice.multiply(longCount).multiply(multiples))
                .add(subtractPrice.multiply(shortCount).multiply(BigDecimal.valueOf(-1)).multiply(multiples));
        BigDecimal subSum = newSum.subtract(sum);

        BigDecimal tradeFee = subSum.abs().multiply(BigDecimal.valueOf(0.01)).setScale(4, RoundingMode.HALF_DOWN);
        sum = newSum.subtract(tradeFee);
        lastPrice = price;
        log.info("B{} T{} F{} L{} S{}", sum, subSum, tradeFee, longCount, shortCount);
        max = max.compareTo(sum) > 0 ? max : sum;
        min = min.compareTo(sum) < 0 ? min : sum;
        return sum;
    }

}
