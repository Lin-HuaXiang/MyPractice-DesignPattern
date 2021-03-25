package com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator;

import java.math.BigDecimal;

import com.example.designpatternspider.selenium.huobi.api.HuobiLinearSwapSpider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MacdWebsiteIndicatorLink extends IndicatorLink {

    // Record the number of calls
    private Integer count = 0;

    private BigDecimal lastMacd = BigDecimal.ZERO;
    private BigDecimal lastDif = BigDecimal.ZERO;
    private BigDecimal lastSubMacd = BigDecimal.ZERO;
    private BigDecimal lastSubDif = BigDecimal.ZERO;
    private BigDecimal lastPrice = BigDecimal.ZERO;

    public MacdWebsiteIndicatorLink(String windowHandle) {
        super(windowHandle);
    }

    @Override
    public void calc(WebDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        // TODO Auto-generated method stub
        // Close ad
        HuobiLinearSwapSpider.closeAd(driver, driverWait, action);
        // Open the professional panel
        HuobiLinearSwapSpider.openProfession(driver, driverWait, action);
        // Turn on the minute line
        HuobiLinearSwapSpider.openMinute(driver, driverWait, action);
        try {
            // Open the MACD line
            HuobiLinearSwapSpider.openMACD(driver, driverWait, action);
        } finally {
            driver.switchTo().defaultContent();
        }
        // get price
        BigDecimal price = HuobiLinearSwapSpider.getPrice(driver, driverWait, action);
        // switch to k chart
        HuobiLinearSwapSpider.switchToKChart(driver, driverWait, action);
        // refresh macd panel
        HuobiLinearSwapSpider.refreshMacd(driver, driverWait, action);
        // get macd dif
        BigDecimal dif = HuobiLinearSwapSpider.getMacdDif(driver, driverWait, action);
        // get macd
        BigDecimal macd = HuobiLinearSwapSpider.getMacd(driver, driverWait, action);
        // calc sub macd、price、dif
        BigDecimal subDif = dif.subtract(lastDif);
        BigDecimal subMacd = macd.subtract(lastMacd);
        BigDecimal subPrice = price.subtract(lastPrice);
        // back to main iframe
        driver.switchTo().defaultContent();
        // Initialize the last price on the first call
        if (count == 0) {
            lastDif = dif;
            lastMacd = macd;
            lastPrice = price;
        }

        // up channel
        if (macd.compareTo(BigDecimal.ZERO) > 0) {
            if (macd.compareTo(lastMacd) < 0 || dif.compareTo(lastDif) < 0) {
                log.info("Ahead sense, up channel, trend worse");
                signalCloseLong = true;
            }
        }
        // down channel
        if (macd.compareTo(BigDecimal.ZERO) < 0) {
            if (macd.compareTo(lastMacd) > 0 || dif.compareTo(lastDif) > 0) {
                log.info("Ahead sense, down channel, trend worse");
                signalCloseShort = true;
            }
        }

        if (macd.compareTo(BigDecimal.ZERO) >= 0 && lastMacd.compareTo(BigDecimal.ZERO) <= 0) {
            log.info("Gold fork open long, close short");
            signalCloseShort = true;
            signalOpenLong = true;
        }

        // If the deviation changes from a positive number to a negative number, then
        if (macd.compareTo(BigDecimal.ZERO) <= 0 && lastMacd.compareTo(BigDecimal.ZERO) >= 0) {
            log.info("Death fork open short, close long");
            signalCloseLong = true;
            signalOpenShort = true;
        }

        // up channel
        if (macd.compareTo(BigDecimal.ZERO) > 0) {

            // if the deviation ratio is relatively small, then ignore; 0~10 ignore 30~max
            // if two consecutive deviations decrease, then sell;
            // sell
            if (macd.compareTo(lastMacd) < 0 || dif.compareTo(lastDif) < 0) {
                log.info("Upstream channel trend turns worse");
                signalCloseLong = true;
            }

            if ((lastSubMacd.compareTo(BigDecimal.ZERO) <= 0 && subMacd.compareTo(BigDecimal.ZERO) > 0)
                    || (lastSubDif.compareTo(BigDecimal.ZERO) <= 0 && subDif.compareTo(BigDecimal.ZERO) > 0)) {
                log.info("Upstream channel trend turns positive");
                signalCloseShort = true;
                signalOpenLong = true;
            }

        }

        // down channel
        if (macd.compareTo(BigDecimal.ZERO) < 0) {

            if (macd.compareTo(lastMacd) > 0 || dif.compareTo(lastDif) > 0) {
                log.info("Downstream channel trend worsened");
                signalCloseShort = true;
            }

            if ((lastSubMacd.compareTo(BigDecimal.ZERO) >= 0 && subMacd.compareTo(BigDecimal.ZERO) < 0)
                    || (lastSubDif.compareTo(BigDecimal.ZERO) >= 0 && subDif.compareTo(BigDecimal.ZERO) < 0)) {
                log.info("Downtrend channel turns positive");
                signalCloseLong = true;
                signalOpenShort = true;
            }
        }

        // 先打印
        log.info("{}, {}, {}, {}, {}, {}", price, dif, macd);
        lastPrice = price;
        lastDif = dif;
        lastMacd = macd;
        lastSubDif = subDif;
        lastSubMacd = subMacd;
    }

}
