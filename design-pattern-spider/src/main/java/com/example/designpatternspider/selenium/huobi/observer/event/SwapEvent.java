package com.example.designpatternspider.selenium.huobi.observer.event;

import com.example.designpatternspider.selenium.huobi.strategy.trade.trade.ISwapTrade;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface SwapEvent {
    
    void doEvent(ISwapTrade swapTrade, WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception;
}
