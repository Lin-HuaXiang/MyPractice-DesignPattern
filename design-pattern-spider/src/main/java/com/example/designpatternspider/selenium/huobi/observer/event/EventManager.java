package com.example.designpatternspider.selenium.huobi.observer.event;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.example.designpatternspider.selenium.huobi.strategy.trade.trade.ISwapTrade;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventManager {

    public enum EventType {
        OPEN_LONG, OPEN_SHORT, CLOSE_LONG, CLOSE_SHORT;
    }

    EnumMap<EventType, SwapEvent> listeners = new EnumMap<>(EventType.class);

    public void subscribe(EventType eventType, SwapEvent swapEvent) {
        listeners.put(eventType, swapEvent);
    }

    public void unsubscribe(EventType eventType) {
        listeners.remove(eventType);
    }

    public void notify(EventType eventType, ISwapTrade swapTrade, ChromeDriver driver, WebDriverWait driverWait, Actions action)
            throws Exception {
        SwapEvent swapEvent = listeners.get(eventType);
        swapEvent.doEvent(swapTrade, driver, driverWait, action);
    }

}
