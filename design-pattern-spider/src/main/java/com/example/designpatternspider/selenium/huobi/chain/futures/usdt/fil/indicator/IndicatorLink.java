package com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * chain and template pattern
 */
@Slf4j
public abstract class IndicatorLink {

    protected String windowHandle = "";

    // Event signal to determine whether the transaction needs to be triggered
    protected Boolean signalOpenLong = false;
    protected Boolean signalOpenShort = false;
    protected Boolean signalCloseLong = false;
    protected Boolean signalCloseShort = false;

    public Boolean getSignalOpenLong() {
        return signalOpenLong;
    }

    public Boolean getSignalOpenShort() {
        return signalOpenShort;
    }

    public Boolean getSignalCloseLong() {
        return signalCloseLong;
    }

    public Boolean getSignalCloseShort() {
        return signalCloseShort;
    }

    private IndicatorLink next;

    protected IndicatorLink(String windowHandle) {
        this.windowHandle = windowHandle;
    }

    private IndicatorLink next() {
        return next;
    }

    public void reset() {
        signalOpenLong = false;
        signalOpenShort = false;
        signalCloseLong = false;
        signalCloseShort = false;
    }

    protected abstract void calc(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception;

    public IndicatorLink appendLink(IndicatorLink next) {
        this.next = next;
        return this;
    }

    public void work(TradeSignal tradeSignal, WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        // calc current
        calc(driver, driverWait, action);
        // append signal
        appendSignal(tradeSignal);
        // calc next
        // log.info("{} {} {} {}", signalOpenLong, signalOpenShort, signalCloseLong, signalCloseShort);
        if (next != null) {
            next().work(tradeSignal, driver, driverWait, action);
        }
        // Reset the state only when the following tasks are completed 
        reset();
    }

    private void appendSignal(TradeSignal tradeSignal) {
        tradeSignal.setSignalOpenLong(tradeSignal.getSignalOpenLong() && signalOpenLong);
        tradeSignal.setSignalOpenShort(tradeSignal.getSignalOpenShort() && signalOpenShort);
        tradeSignal.setSignalCloseLong(tradeSignal.getSignalCloseLong() && signalCloseLong);
        tradeSignal.setSignalCloseShort(tradeSignal.getSignalCloseShort() && signalCloseShort);
    }
}
