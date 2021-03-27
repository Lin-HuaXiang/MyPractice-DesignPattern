package com.example.designpatternspider.selenium.huobi.strategy.futures.usdt.fil;

import java.math.BigDecimal;

import com.example.designpatternspider.selenium.huobi.strategy.trade.trade.ISwapTrade;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockFilSwapTrade implements ISwapTrade {

    private Integer duoCount = 0;
    private Integer kongCount = 0;
    private Integer duoRepository = 100;
    private Integer kongRepository = 100;
    private BigDecimal lastPrice = BigDecimal.ZERO;
    private BigDecimal sum = BigDecimal.valueOf(10000);

    @Override
    public void openLong(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        int use = this.duoRepository / 2;
        this.duoRepository -= use;
        this.duoCount += use;
    }

    @Override
    public void openShort(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        int use = this.kongRepository / 2;
        this.kongRepository -= use;
        this.kongCount += use;
    }

    @Override
    public void closeLong(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        this.duoRepository += this.duoCount;
        int use = this.duoCount;
        this.duoCount = 0;
    }

    @Override
    public void closeShort(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        this.kongRepository += this.kongCount;
        int use = this.kongCount;
        this.kongCount = 0;
    }

    @Override
    public BigDecimal getTotalEquity(WebDriver driver, BigDecimal price) throws Exception {
        BigDecimal subtractPrice = price.subtract(this.lastPrice);
        sum = sum.add(subtractPrice.multiply(BigDecimal.valueOf(duoCount)))
                .add(subtractPrice.multiply(BigDecimal.valueOf(kongCount)).multiply(BigDecimal.valueOf(-1)));
        this.lastPrice = price;
        log.info("balance {}, {}, {}", sum, duoCount, kongCount);
        return sum;
    }

}
