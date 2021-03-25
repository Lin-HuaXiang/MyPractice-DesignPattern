package com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil;

import com.example.designpatternspider.selenium.huobi.observer.event.SwapEvent;
import com.example.designpatternspider.selenium.huobi.strategy.trade.trade.ISwapTrade;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloseShortSwapEvent implements SwapEvent {

    @Override
    public void doEvent(ISwapTrade swapTrade, WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        swapTrade.closeShort(driver, driverWait, action);
    }
    
}
