package com.example.designpatternspider.selenium.huobi.strategy.trade.trade;

import java.math.BigDecimal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface ISwapTrade {

    void openLong(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception;

    void openShort(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception;

    void closeLong(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception;

    void closeShort(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception;
 
    BigDecimal getTotalEquity(WebDriver driver, BigDecimal price) throws Exception;
}
