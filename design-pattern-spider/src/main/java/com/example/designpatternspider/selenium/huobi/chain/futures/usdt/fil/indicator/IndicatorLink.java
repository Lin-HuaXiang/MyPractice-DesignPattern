package com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * chain and template pattern
 */
public abstract class IndicatorLink {

    protected String windowHandle = "";

    // Event signal to determine whether the transaction needs to be triggered
    protected Boolean signalOpenLong = false;
    protected Boolean signalOpenShort = false;
    protected Boolean signalCloseLong = false;
    protected Boolean signalCloseShort = false;

    private IndicatorLink next;

    protected IndicatorLink(String windowHandle) {
        this.windowHandle = windowHandle;
    }

    private IndicatorLink next() {
        // transfer trade signals
        next.signalOpenLong = this.signalOpenLong;
        next.signalOpenShort = this.signalOpenShort;
        next.signalCloseLong = this.signalCloseLong;
        next.signalCloseShort = this.signalCloseShort;
        return next;
    }

    private void reset() {
        signalOpenLong = false;
        signalOpenShort = false;
        signalCloseLong = false;
        signalCloseShort = false;
    }

    protected abstract void calc(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception;

    public IndicatorLink appendLink(IndicatorLink next) {
        this.next = next;
        return this;
    }

    public void work(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        // calc current
        calc(driver, driverWait, action);
        // calc next
        if (next != null) {
            next().work(driver, driverWait, action);
        }
        // Reset the state only when the following tasks are completed 
        reset();
    }
}
