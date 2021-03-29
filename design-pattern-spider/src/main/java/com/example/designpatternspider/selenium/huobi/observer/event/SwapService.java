package com.example.designpatternspider.selenium.huobi.observer.event;

import java.math.BigDecimal;

import com.example.designpatternspider.selenium.huobi.api.HuobiLinearSwapSpider;
import com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator.IndicatorLink;
import com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator.TradeSignal;
import com.example.designpatternspider.selenium.huobi.observer.event.EventManager.EventType;
import com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil.CloseLongSwapEvent;
import com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil.CloseShortSwapEvent;
import com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil.OpenLongSwapEvent;
import com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil.OpenShortSwapEvent;
import com.example.designpatternspider.selenium.huobi.strategy.trade.trade.ISwapTrade;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SwapService {

    private ISwapTrade swapTrade;

    private EventManager eventManager;

    public SwapService(ISwapTrade swapTrade) {
        this.swapTrade = swapTrade;
        eventManager = new EventManager();
        eventManager.subscribe(EventType.OPEN_LONG, new OpenLongSwapEvent());
        eventManager.subscribe(EventType.OPEN_SHORT, new OpenShortSwapEvent());
        eventManager.subscribe(EventType.CLOSE_LONG, new CloseLongSwapEvent());
        eventManager.subscribe(EventType.CLOSE_SHORT, new CloseShortSwapEvent());
    }

    public void notify(TradeSignal tradeSignal, WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        BigDecimal price = HuobiLinearSwapSpider.getPrice(driver, driverWait, action);
        swapTrade.getTotalEquity(driver, price);
        if (Boolean.TRUE.equals(tradeSignal.getSignalOpenLong())) {
            eventManager.notify(EventType.OPEN_LONG, swapTrade, driver, driverWait, action);
        }
        if (Boolean.TRUE.equals(tradeSignal.getSignalOpenShort())) {
            eventManager.notify(EventType.OPEN_SHORT, swapTrade, driver, driverWait, action);
        }
        if (Boolean.TRUE.equals(tradeSignal.getSignalCloseLong())) {
            eventManager.notify(EventType.CLOSE_LONG, swapTrade, driver, driverWait, action);
        }
        if (Boolean.TRUE.equals(tradeSignal.getSignalCloseShort())) {
            eventManager.notify(EventType.CLOSE_SHORT, swapTrade, driver, driverWait, action);
        }
    }








    
}
