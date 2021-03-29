package com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator;

import java.math.BigDecimal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KdjWebsiteIndicatorLink extends IndicatorLink {

    private int count = 0;
    private BigDecimal k = BigDecimal.ZERO;
    private BigDecimal d = BigDecimal.ZERO;
    private BigDecimal j = BigDecimal.ZERO;
    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal lastK = BigDecimal.ZERO;
    private BigDecimal lastD = BigDecimal.ZERO;
    private BigDecimal lastJ = BigDecimal.ZERO;
    private BigDecimal lastPrice = BigDecimal.ZERO;

    public KdjWebsiteIndicatorLink(String windowHandle) {
        super(windowHandle);
    }

    @Override
    protected void calc(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        // TODO Auto-generated method stub
        driver.switchTo().window(windowHandle);

        // gold fork
        if (lastK.compareTo(lastD) <= 0 && k.compareTo(d) > 0) {
            log.info("kdj gold fork");
            signalCloseShort = true;
            signalOpenLong = true;
        }

        // death fork
        if (lastK.compareTo(lastD) >= 0 && k.compareTo(d) < 0) {
            log.info("kdj death fork");
            signalCloseLong = true;
            signalOpenShort = true;
        }

        // 先打印
        log.info("{}-{}, {}-{}, {}-{}", price, lastPrice, k, lastK, d, lastD);

        if (count % 5 == 0) {
            lastPrice = price;
            lastK = k;
            lastD = d;
            lastJ = j;
        }

        count++;

    }

}
