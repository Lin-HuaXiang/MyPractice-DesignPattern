package com.example.designpatternspider.selenium.huobi.strategy.futures.usdt.fil.trade;

import java.math.BigDecimal;

import com.example.designpatternspider.selenium.exception.MyException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface IFilSwapTrade {

    void openLong(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception;

    void openShort(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception;

    void closeLong(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception;

    void closeShort(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception;
 
    BigDecimal getTotalEquity(ChromeDriver driver, BigDecimal price) throws Exception;
}
