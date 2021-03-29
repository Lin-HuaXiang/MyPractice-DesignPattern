package com.example.designpatternspider.selenium.huobi.strategy.futures.usdt.fil;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.designpatternspider.selenium.huobi.strategy.trade.trade.ISwapTrade;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockFilSwapTrade implements ISwapTrade {

    private Integer lastLongCount = 0;
    private Integer lastShortCount = 0;
    private Integer longCount = 0;
    private Integer shortCount = 0;
    private Integer longRepository = 100;
    private Integer shortRepository = 100;
    private BigDecimal lastPrice = BigDecimal.ZERO;
    private BigDecimal sum = BigDecimal.valueOf(10000);

    @Override
    public void openLong(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        int use = this.longRepository / 2;
        this.longRepository -= use;
        this.longCount += use;
    }

    @Override
    public void openShort(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        int use = this.shortRepository / 2;
        this.shortRepository -= use;
        this.shortCount += use;
    }

    @Override
    public void closeLong(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        this.longRepository += this.longCount;
        int use = this.longCount;
        this.longCount = 0;
    }

    @Override
    public void closeShort(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        this.shortRepository += this.shortCount;
        int use = this.shortCount;
        this.shortCount = 0;
    }

    @Override
    public BigDecimal getTotalEquity(WebDriver driver, BigDecimal price) throws Exception {
        if (lastPrice.compareTo(BigDecimal.ZERO) == 0) {
            lastPrice = price;
        }
        BigDecimal subtractPrice = price.subtract(this.lastPrice);
        BigDecimal newSum = sum.add(subtractPrice.multiply(BigDecimal.valueOf(longCount)))
                .add(subtractPrice.multiply(BigDecimal.valueOf(shortCount)).multiply(BigDecimal.valueOf(-1)));
        BigDecimal subSum = newSum.subtract(sum);

        BigDecimal tradeFee = subSum.abs().multiply(BigDecimal.valueOf(0.01)).setScale(4, RoundingMode.HALF_DOWN);
        sum = newSum.subtract(tradeFee);
        this.lastPrice = price;
        log.info("b{}, t{}, f{}, l{}, s{}", sum, subSum, tradeFee, longCount, shortCount);
        return sum;
    }

}
