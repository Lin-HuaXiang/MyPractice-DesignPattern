package com.example.designpatternspider.selenium.huobi.mock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.example.designpatternspider.selenium.huobi.po.Signal;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

import org.apache.poi.xwpf.usermodel.BreakClear;
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

    public void printResultMock(Map<String, String> marketStatusMap, List<ReviewExport> reviewExports) {
        for (ReviewExport reviewExport : reviewExports) {
            if (min != null && min.compareTo(BigDecimal.ZERO) < 0) {
                log.info("broken repository");
                break;
            }
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
            signal = new Signal();
           lowerJudge(lowerData, reviewExport.getPrice(), signal);
           calcResultMock(signal);
            
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
        
        BigDecimal temp = sum;

        BigDecimal longVolumeMulti = longCount.multiply(BigDecimal.TEN);
        BigDecimal shortVolumeMulti = shortCount.multiply(BigDecimal.TEN);

        BigDecimal lowLongSubMarginBalance = longVolumeMulti.divide(lastPrice, 6, RoundingMode.HALF_DOWN)
                .subtract(longVolumeMulti.divide(low, 6, RoundingMode.HALF_DOWN));

        BigDecimal lowShortSubMarginBalance = shortVolumeMulti.divide(low, 6, RoundingMode.HALF_DOWN)
                .subtract(shortVolumeMulti.divide(lastPrice, 6, RoundingMode.HALF_DOWN));

        BigDecimal lowMarginBalance = temp.add(lowLongSubMarginBalance).add(lowShortSubMarginBalance);


        BigDecimal highLongSubMarginBalance = longVolumeMulti.divide(lastPrice, 6, RoundingMode.HALF_DOWN)
                .subtract(longVolumeMulti.divide(high, 6, RoundingMode.HALF_DOWN));

        BigDecimal highShortSubMarginBalance = shortVolumeMulti.divide(high, 6, RoundingMode.HALF_DOWN)
                .subtract(shortVolumeMulti.divide(lastPrice, 6, RoundingMode.HALF_DOWN));


        BigDecimal highMarginBalance = temp.add(highLongSubMarginBalance).add(highShortSubMarginBalance);

        BigDecimal longSubMarginBalance = longVolumeMulti.divide(lastPrice, 6, RoundingMode.HALF_DOWN)
                .subtract(longVolumeMulti.divide(price, 6, RoundingMode.HALF_DOWN));

        BigDecimal shortSubMarginBalance = shortVolumeMulti.divide(price, 6, RoundingMode.HALF_DOWN)
                .subtract(shortVolumeMulti.divide(lastPrice, 6, RoundingMode.HALF_DOWN));

        BigDecimal subSum = longSubMarginBalance.add(shortSubMarginBalance);
        BigDecimal tradeFee = subSum.abs().multiply(BigDecimal.valueOf(0.01)).setScale(4, RoundingMode.HALF_DOWN);
        sum = sum.add(subSum).subtract(tradeFee);

        if (max == null) {
            max = sum;
        }
        
        if (min == null) {
            min = sum;
        }

        List<BigDecimal> asList1 = Arrays.asList(sum, lowMarginBalance, highMarginBalance);
        log.info("[B]{}[P]{}->{}[MI]{} {}[MA]{} {}[L]{}[S]{}", sum, lastPrice, price, low, Collections.min(asList1), high, Collections.max(asList1), longCount, shortCount);
        lastPrice = price;
        List<BigDecimal> asList2 = Arrays.asList(max, min, sum, lowMarginBalance, highMarginBalance);
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
                BigDecimal shortVolumeMulti = shortCount.multiply(BigDecimal.TEN);
                BigDecimal subMarginBalance = shortVolumeMulti.divide(price, 6, RoundingMode.HALF_DOWN)
                        .subtract(shortVolumeMulti.divide(lowPrice, 6, RoundingMode.HALF_DOWN));
                if (subMarginBalance.compareTo(BigDecimal.ZERO) != 0
                        && subMarginBalance.divide(sum, 6, RoundingMode.HALF_DOWN).abs().compareTo(BigDecimal.valueOf(0.15)) > 0) {
                    log.info("---[T]{} [B]{} [SUB]{}  [P]{}->{} [L]{}[S]{} close short" , reviewExport.getTime(), sum, subMarginBalance.abs() , price, lowPrice, longCount, shortCount);
                    signal.setSignalCloseShort(true);
                    getTotalEquity(reviewExport.getPrice(), reviewExport.getLow(), reviewExport.getHigh());
                    closeShort();
                    closeLong();
                    break;
                }
            } else if (subtractPrice.compareTo(BigDecimal.ZERO) < 0) {
                BigDecimal shortVolumeMulti = longCount.multiply(BigDecimal.TEN);
                BigDecimal subMarginBalance = shortVolumeMulti.divide(price, 6, RoundingMode.HALF_DOWN)
                        .subtract(shortVolumeMulti.divide(lowPrice, 6, RoundingMode.HALF_DOWN));
                if (subMarginBalance.compareTo(BigDecimal.ZERO) != 0 && subMarginBalance.divide(sum, 6, RoundingMode.HALF_DOWN).abs().compareTo(BigDecimal.valueOf(0.15)) > 0) {
                    log.info("---[T]{} [B]{} [SUB]{}  [P]{}->{} [L]{}[S]{} close long" , reviewExport.getTime(), sum, subMarginBalance.abs(), price, lowPrice, longCount, shortCount);
                    signal.setSignalCloseLong(true);
                    getTotalEquity(reviewExport.getPrice(), reviewExport.getLow(), reviewExport.getHigh());
                    closeShort();
                    closeLong();
                    break;
                }
            }

        }
    }

}
