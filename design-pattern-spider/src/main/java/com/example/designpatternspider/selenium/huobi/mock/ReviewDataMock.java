package com.example.designpatternspider.selenium.huobi.mock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.designpatternspider.selenium.huobi.po.Signal;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

import org.springframework.util.CollectionUtils;
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
        repository = decimal.multiply(multiples);
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume.multiply(multiples) ;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    private BigDecimal longCount = BigDecimal.ZERO;
    private BigDecimal shortCount = BigDecimal.ZERO;
    private BigDecimal repository = BigDecimal.valueOf(100);
    private BigDecimal sum = BigDecimal.valueOf(10000);
    private BigDecimal max;
    private BigDecimal min;
    private BigDecimal lastPrice;
    private BigDecimal multiples = BigDecimal.ONE;
    private BigDecimal volume;

    public void printResultMock(List<ReviewExport> reviewExports) {
        for (ReviewExport reviewExport : reviewExports) {
            Signal signal = new Signal();
            String time = reviewExport.getTime().replace("-", "").replace(" ", "").replace(":", "");
            log.info("[T]{}", time);
            // log.info("[{}]{}", status.toUpperCase(), key);
            for (ReviewDataMockIndicator reviewDataMockIndicator : reviewDataMockIndicators) {
                Signal temp = reviewDataMockIndicator.calcMock(reviewExport);
                // Signaling between different indicators
                signal.unite(temp);
                reviewDataMockIndicator.resetSignal();
            }
            getTotalEquity(reviewExport.getPrice(), reviewExport.getLow(), reviewExport.getHigh());
            calcResultMock(signal);
            List<ReviewExport> lowerData = reviewExport.getLowerData();
           lowerJudge(lowerData, reviewExport.getPrice(), signal);
            
        }
        log.info("[MI]{} [MA]{}", min, max);
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
        if (volume == null) {
            BigDecimal use = repository.divide(BigDecimal.valueOf(2), 6, RoundingMode.HALF_DOWN);
            repository =  repository.subtract(use);
            longCount = longCount.add(use);
        } else {
            if (repository.compareTo(volume) > 0) {
                repository =  repository.subtract(volume);
                longCount = longCount.add(volume);
            }
        }
    }

    private void openShort() {
        if (volume == null) {
            BigDecimal use = repository.divide(BigDecimal.valueOf(2), 6, RoundingMode.HALF_DOWN);
            repository = repository.subtract(use);
            shortCount = shortCount.add(use);
        } else {
            if (repository.compareTo(volume) > 0) {
                repository = repository.subtract(volume);
                shortCount = shortCount.add(volume);
            }
        }
    }

    private void closeLong() {
        repository = repository.add(longCount);
        BigDecimal use = longCount;
        longCount = BigDecimal.ZERO;
    }

    private void closeShort() {
        repository = repository.add(shortCount);
        BigDecimal use = shortCount;
        shortCount = BigDecimal.ZERO;
    }

    private BigDecimal getTotalEquity(BigDecimal price, BigDecimal low, BigDecimal high) {
        if (ObjectUtils.isEmpty(lastPrice)) {
            lastPrice = price;
        }
        BigDecimal subtractPrice = price.subtract(lastPrice);
        BigDecimal temp = sum;
        BigDecimal newSum = sum.add(subtractPrice.multiply(longCount))
                .add(subtractPrice.multiply(shortCount).multiply(BigDecimal.valueOf(-1)));
        BigDecimal subSum = newSum.subtract(sum);
        BigDecimal tradeFee = subSum.abs().multiply(BigDecimal.valueOf(0.01)).setScale(4, RoundingMode.HALF_DOWN);
        sum = newSum.subtract(tradeFee);

        subtractPrice = price.subtract(low);
        BigDecimal lowSum = temp.add(subtractPrice.multiply(longCount))
        .add(subtractPrice.multiply(shortCount).multiply(BigDecimal.valueOf(-1)));

        subtractPrice = price.subtract(high);
        BigDecimal highSum = temp.add(subtractPrice.multiply(longCount))
        .add(subtractPrice.multiply(shortCount).multiply(BigDecimal.valueOf(-1)));

        if (max == null) {
            max = highSum;
        }
        if (min == null) {
            min = lowSum;
        }


        List<BigDecimal> asList1 = Arrays.asList(sum, lowSum, highSum);
        log.info("[B]{}[P]{}->{}[MI]{}[MA]{}[L]{}[S]{}", sum, lastPrice, price, Collections.min(asList1), Collections.max(asList1), longCount, shortCount);
        lastPrice = price;
        List<BigDecimal> asList2 = Arrays.asList(max, min, sum, lowSum, highSum);
        max = Collections.max(asList2);
        min = Collections.min(asList2);
        return sum;
    }

    public void lowerJudge(List<ReviewExport> lowerData, BigDecimal price, Signal signal) {
        if (CollectionUtils.isEmpty(lowerData)) {
            return;
        }
        for (ReviewExport reviewExport : lowerData) {
            BigDecimal lowPrice = reviewExport.getPrice();
            BigDecimal subtractPrice = price.subtract(lowPrice);
            if (subtractPrice.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal subSum = subtractPrice.multiply(shortCount).multiply(BigDecimal.valueOf(-1));
                if (subSum.compareTo(BigDecimal.ZERO) != 0 && subSum.divide(sum, 6, RoundingMode.HALF_DOWN).abs().compareTo(BigDecimal.valueOf(0.2)) > 0) {
                    log.info("---[T]{} [B]{} [SUB]{}  [P]{}->{} [L]{}[S]{} close short" , reviewExport.getTime(), sum, subSum , price, lowPrice, longCount, shortCount);
                    signal.setSignalCloseShort(true);
                    getTotalEquity(reviewExport.getPrice(), reviewExport.getLow(), reviewExport.getHigh());
                    closeShort();
                    break;
                }
            } else if (subtractPrice.compareTo(BigDecimal.ZERO) < 0) {
                BigDecimal subSum = subtractPrice.multiply(longCount).multiply(BigDecimal.valueOf(1));
                if (subSum.compareTo(BigDecimal.ZERO) != 0 && subSum.divide(sum, 6, RoundingMode.HALF_DOWN).abs().compareTo(BigDecimal.valueOf(0.2)) > 0) {
                    log.info("---[T]{} [B]{} [SUB]{}  [P]{}->{} [L]{}[S]{} close long" , reviewExport.getTime(), sum, subSum, price, lowPrice, longCount, shortCount);
                    signal.setSignalCloseLong(true);
                    getTotalEquity(reviewExport.getPrice(), reviewExport.getLow(), reviewExport.getHigh());
                    closeLong();
                    break;
                }
            }

        }
    }

}
