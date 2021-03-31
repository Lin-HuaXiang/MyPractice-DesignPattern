package com.example.designpatternspider.selenium.huobi.mock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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

    private Integer longCount = 0;
    private Integer shortCount = 0;
    private Integer longRepository = 100;
    private Integer shortRepository = 100;
    private BigDecimal lastPrice;
    private BigDecimal sum = BigDecimal.valueOf(10000);
    private BigDecimal max = BigDecimal.valueOf(Integer.MIN_VALUE);
    private BigDecimal min = BigDecimal.valueOf(Integer.MAX_VALUE);
    private BigDecimal multiples;

    public void printResultMock(List<ReviewExport> reviewExports) {
        for (ReviewExport reviewExport : reviewExports) {
            for (ReviewDataMockIndicator reviewDataMockIndicator : reviewDataMockIndicators) {
                reviewDataMockIndicator.calcMock(reviewExport);
                getTotalEquity(reviewExport.getPrice());
                calcResultMock(reviewDataMockIndicator);
                reviewDataMockIndicator.resetSignal();
            }
        }
        log.info("{}, {}", min, max);

    }

    private void calcResultMock(ReviewDataMockIndicator reviewDataMockIndicator) {
        if (Boolean.TRUE.equals(reviewDataMockIndicator.getSignalOpenLong())) {
            openLong();
        }
        if (Boolean.TRUE.equals(reviewDataMockIndicator.getSignalOpenShort())) {
            openShort();
        }
        if (Boolean.TRUE.equals(reviewDataMockIndicator.getSignalCloseLong())) {
            closeLong();
        }
        if (Boolean.TRUE.equals(reviewDataMockIndicator.getSignalCloseShort())) {
            closeShort();
        }
    }

    private void openLong() {
        int use = longRepository / 2;
        longRepository -= use;
        longCount += use;
    }

    private void openShort() {
        int use = shortRepository / 2;
        shortRepository -= use;
        shortCount += use;
    }

    private void closeLong() {
        longRepository += longCount;
        int use = longCount;
        longCount = 0;
    }

    private void closeShort() {
        shortRepository += shortCount;
        int use = shortCount;
        shortCount = 0;
    }

    private BigDecimal getTotalEquity(BigDecimal price) {
        if (ObjectUtils.isEmpty(lastPrice)) {
            lastPrice = price;
        }
        BigDecimal subtractPrice = price.subtract(lastPrice);
        BigDecimal newSum = sum.add(subtractPrice.multiply(BigDecimal.valueOf(longCount)).multiply(multiples))
                .add(subtractPrice.multiply(BigDecimal.valueOf(shortCount)).multiply(BigDecimal.valueOf(-1)).multiply(multiples));
        BigDecimal subSum = newSum.subtract(sum);

        BigDecimal tradeFee = subSum.abs().multiply(BigDecimal.valueOf(0.01)).setScale(4, RoundingMode.HALF_DOWN);
        sum = newSum.subtract(tradeFee);
        lastPrice = price;
        log.info("b{}, t{}, f{}, l{}, s{}", sum, subSum, tradeFee, longCount, shortCount);
        max = max.compareTo(sum) > 0 ? max : sum;
        min = min.compareTo(sum) < 0 ? min : sum;
        return sum;
    }

}
