package com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.designpatternspider.selenium.huobi.api.HuoBiOpenSpider;
import com.example.designpatternspider.selenium.huobi.po.HuoBiKline;
import com.example.designpatternspider.selenium.util.RsiIndicator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RsiIndicatorLink extends IndicatorLink {

    // last rsi 9 value
    private BigDecimal lastRsi9 = BigDecimal.ZERO;
    // last rsi 12 value
    private BigDecimal latRsi12 = BigDecimal.ZERO;
    // last rsi 72 value
    private BigDecimal lastRsi72 = BigDecimal.ZERO;
    // last price value
    private BigDecimal lastPrice = BigDecimal.ZERO;
    // Record the number of calls
    private Integer count = 0;

    public RsiIndicatorLink(String windowHandle) {
        super(windowHandle);
    }

    @Override
    public void calc(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception {

        driver.switchTo().window(windowHandle);
        
        Thread.sleep(1000);
        // url
        HuoBiOpenSpider.getMain(driver);
        // fil usdt
        HuoBiOpenSpider.inputSymbol(driver, action, "filusdt");
        // 1min
        HuoBiOpenSpider.inputPeriod(driver, action, "5min");
        // 72
        HuoBiOpenSpider.inputSize(driver, action, "72");

        HuoBiOpenSpider.sendRequest(driver, action);
        HuoBiKline kline72 = HuoBiOpenSpider.getData(driver);

        // calculate rsi 9 12 72 14 value
        BigDecimal calcRsi9 = RsiIndicator.calcRsi(kline72.getData().subList(0, 9));
        BigDecimal calcRsi12 = RsiIndicator.calcRsi(kline72.getData().subList(0, 12));
        BigDecimal calcRsi14 = RsiIndicator.calcRsi(kline72.getData().subList(0, 14));
        BigDecimal calcRsi72 = RsiIndicator.calcRsi(kline72.getData());

        // current price
        BigDecimal price = kline72.getData().get(0).getClose().setScale(3, RoundingMode.UP);

        // Initialize the last price on the first call 
        if (count == 0) {
            lastPrice = price;
        }

        if (lastRsi9.compareTo(lastRsi72) < 0 && calcRsi9.compareTo(calcRsi72) > 0
                && calcRsi12.compareTo(calcRsi72) > 0) {
            log.info("up cross");
            signalOpenLong = true;
            signalCloseShort = true;
        }

        if (lastRsi9.compareTo(lastRsi72) > 0 && calcRsi9.compareTo(calcRsi72) < 0
                && calcRsi12.compareTo(calcRsi72) < 0) {
            log.info("down cross");
            signalOpenShort = true;
            signalCloseLong = true;
        }

        // The heat is too high, need to adapt to the macd mac line drop
        if (calcRsi9.divide(calcRsi72, 2, RoundingMode.HALF_DOWN).compareTo(BigDecimal.valueOf(1.45)) >= 0) {
            log.info("touch highest");
            signalOpenShort = true;
            signalCloseLong = true;
        }

        // The heat is too high, need to adjust to MACD line drop
        if (calcRsi9.divide(calcRsi72, 2, RoundingMode.HALF_DOWN).compareTo(BigDecimal.valueOf(0.55)) <= 0) {
            log.info("touch lowest");
            signalCloseShort = true;
            signalCloseLong = true;
        }

        // reverse
        log.info("{}-{}, {}-{}, {}-{}, {}-{}]", price, calcRsi14, lastRsi9, calcRsi9, latRsi12, calcRsi12,
                lastRsi72, calcRsi72);

        lastRsi9 = calcRsi9;
        latRsi12 = calcRsi12;
        lastRsi72 = calcRsi72;


        // reset signal
        reset();
    }

}
