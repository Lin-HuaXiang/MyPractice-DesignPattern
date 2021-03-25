package com.example.designpatternspider.selenium.huobi.observer.event;

import com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator.IndicatorLink;
import com.example.designpatternspider.selenium.huobi.observer.event.EventManager.EventType;
import com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil.CloseLongSwapEvent;
import com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil.CloseShortSwapEvent;
import com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil.OpenLongSwapEvent;
import com.example.designpatternspider.selenium.huobi.observer.futrues.usdt.fil.OpenShortSwapEvent;
import com.example.designpatternspider.selenium.huobi.strategy.trade.trade.ISwapTrade;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwapService {

    private ISwapTrade swapTrade;

    private EventManager eventManager;

    public SwapService(ISwapTrade swapTrade) {
        eventManager = new EventManager();
        eventManager.subscribe(EventType.OPEN_LONG, new OpenLongSwapEvent());
        eventManager.subscribe(EventType.OPEN_SHORT, new OpenShortSwapEvent());
        eventManager.subscribe(EventType.CLOSE_LONG, new CloseLongSwapEvent());
        eventManager.subscribe(EventType.CLOSE_SHORT, new CloseShortSwapEvent());
        this.swapTrade = swapTrade;
    }

    public void notify(IndicatorLink indicatorLink, WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        if (Boolean.TRUE.equals(indicatorLink.getSignalOpenLong())) {
            eventManager.notify(EventType.OPEN_LONG, swapTrade, driver, driverWait, action);
        }
        if (Boolean.TRUE.equals(indicatorLink.getSignalOpenShort())) {
            eventManager.notify(EventType.OPEN_SHORT, swapTrade, driver, driverWait, action);
        }
        if (Boolean.TRUE.equals(indicatorLink.getSignalCloseLong())) {
            eventManager.notify(EventType.CLOSE_LONG, swapTrade, driver, driverWait, action);
        }
        if (Boolean.TRUE.equals(indicatorLink.getSignalCloseShort())) {
            eventManager.notify(EventType.CLOSE_SHORT, swapTrade, driver, driverWait, action);
        }
    }








    
}
