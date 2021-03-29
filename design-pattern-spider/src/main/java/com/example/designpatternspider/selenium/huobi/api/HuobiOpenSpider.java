package com.example.designpatternspider.selenium.huobi.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.example.designpatternspider.selenium.huobi.po.HuoBiKline;
import com.example.designpatternspider.selenium.util.RsiIndicator;
import com.example.designpatternspider.selenium.util.SpiderUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HuobiOpenSpider {

    static Integer duoCount = 0;
    static Integer kongCount = 0;
    static Integer duoRepository = 10;
    static Integer kongRepository = 10;
    static BigDecimal sum = new BigDecimal("1000");

    public static final String API_URL = "https://open.huobigroup.com/?name=kline";

    public static void main(String[] args) throws Exception {
        WebDriver driver = driverBuilderChromeHeadLess();
        Actions action = new Actions(driver);

        SpiderUtil.requestData(API_URL, driver);

        // 一)将14天上升的数目相加，除以14，上例中总共上升16除以14得1.143(精确到小数点后三位)；
        // (二)将14天下跌的数目相加，除以14，上例中总共下跌23除以14得1.643(精确到小数点后三位)；
        // (三)求出相对强度RS，即RS=1.143/1.643=0.696%(精确到小数点后三位)；
        // (四)1+RS=1+0.696=1.696;
        // (五)以100除以1+RS，即100/1.696=58.962;
        // (六)100-58.962=41.038。

        // filusdt
        inputSymbol(driver, action, "filusdt");
        // 1min
        inputPeriod(driver, action, "5min");
        // 72
        inputSize(driver, action, "72");

        int n = 1440;

        // BigDecimal lastCalcRsi9 = BigDecimal.ZERO;
        // BigDecimal lastCalcRsi12 = BigDecimal.ZERO;
        // BigDecimal lastCalcRsi72 = BigDecimal.ZERO;

        Calendar calendar = Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        // int subMinute = 4 - minute % 5;
        // int subSecond = 60 - second;
        // int fix = subMinute * 60 + subSecond;
        // log.info("time fix {}", fix);
        // Thread.sleep((fix) * 1000);

        log.info("time fix {}", 60 - second);
        Thread.sleep((60 - second) * 1000);

        BigDecimal subRsi9 = BigDecimal.ZERO;
        BigDecimal subRsi12 = BigDecimal.ZERO;
        BigDecimal subRsi72 = BigDecimal.ZERO;
        BigDecimal lastPrice = BigDecimal.ZERO;

        for (int i = 0; i < n; i++) {

            sendRequest(driver, action);
            HuoBiKline kline72 = getData(driver);

            BigDecimal calcRsi9 = RsiIndicator.calcRsi(kline72.getData().subList(0, 9));
            BigDecimal calcRsi12 = RsiIndicator.calcRsi(kline72.getData().subList(0, 12));
            BigDecimal calcRsi14 = RsiIndicator.calcRsi(kline72.getData().subList(0, 14));
            BigDecimal calcRsi72 = RsiIndicator.calcRsi(kline72.getData());

            BigDecimal price = kline72.getData().get(0).getClose().setScale(3, RoundingMode.UP);

            if (i == 0) {
                lastPrice = price;
            }

            if (subRsi9.compareTo(subRsi72) < 0 && calcRsi9.compareTo(calcRsi72) > 0
                    && calcRsi12.compareTo(calcRsi72) > 0) {
                log.info("up cross");
                buyDuo50Percent(i);
                sellKongAll(i);

            }
            if (subRsi9.compareTo(subRsi72) > 0 && calcRsi9.compareTo(calcRsi72) < 0
                    && calcRsi12.compareTo(calcRsi72) < 0) {
                log.info("down cross");
                buyKong50Percent(i);
                sellDuoAll(i);
            }

            // The heat is too high, need to adapt to the macd mac line drop
            if (calcRsi9.compareTo(BigDecimal.valueOf(80)) > 0 && calcRsi14.compareTo(BigDecimal.valueOf(80)) > 0) {
                log.info("touch highest");
                buyKong50Percent(i);
                sellDuoAll(i);
            }

            if (calcRsi9.compareTo(BigDecimal.valueOf(30)) < 0) {
                log.info("touch lowest");
                buyDuo50Percent(i);
                sellDuoAll(i);
            }

            BigDecimal subtractPrice = price.subtract(lastPrice);
            sum = sum.add(subtractPrice.multiply(BigDecimal.valueOf(duoCount)))
                    .add(subtractPrice.multiply(BigDecimal.valueOf(kongCount)).multiply(BigDecimal.valueOf(-1)));

            log.info("[{}] [{}] [{},{}], [[{}], {}<-{}, {}<-{}, {}<-{}]", price, sum, duoCount, kongCount, calcRsi14,
                    calcRsi9, subRsi9, calcRsi12, subRsi12, calcRsi72, subRsi72);

            calendar = Calendar.getInstance();
            second = calendar.get(Calendar.SECOND);
            minute = calendar.get(Calendar.MINUTE);

            if (minute % 5 == 0) {
                subRsi9 = calcRsi9;
                subRsi12 = calcRsi12;
                subRsi72 = calcRsi72;
            }
            lastPrice = price;
            Thread.sleep((60 - second) * 1000);

        }

        driver.quit();
    }

    public static void getMain(WebDriver driver) {
        // url 检测
        if (driver.getCurrentUrl().indexOf(API_URL) < 0) {
            driver.get(API_URL);
        }
    }

    public static void inputSymbol(WebDriver driver, Actions action, String input) throws InterruptedException {
        WebElement symbol = SpiderUtil.getElementUntil(By.xpath(
                "//*[@id=\"app\"]/section/section/main/div/div[1]/div/div[8]/div[1]/div[3]/table/tbody/tr[1]/td[3]/div/div/input"),
                driver, 5);
        if (!input.equals(symbol.getText())) {
            action.sendKeys(symbol, Keys.CONTROL, "a").sendKeys(symbol, input).build().perform();
            Thread.sleep(1000);
        }
    }

    public static void inputPeriod(WebDriver driver, Actions action, String input) throws InterruptedException {
        // support 1min, 5min, 15min, 30min, 60min, 4hour, 1day, 1mon, 1week, 1year
        WebElement period = SpiderUtil.getElementUntil(By.xpath(
                "//*[@id=\"app\"]/section/section/main/div/div[1]/div/div[8]/div[1]/div[3]/table/tbody/tr[2]/td[3]/div/div/input"),
                driver, 5);
        if (!input.equals(period.getText())) {
            action.sendKeys(period, Keys.BACK_SPACE, "a").sendKeys(period, input).build().perform();
            Thread.sleep(1000);
        }
    }

    public static void inputSize(WebDriver driver, Actions action, String input) throws InterruptedException {
        // support [1, 2000]
        WebElement size = SpiderUtil.getElementUntil(By.xpath(
                "//*[@id=\"app\"]/section/section/main/div/div[1]/div/div[8]/div[1]/div[3]/table/tbody/tr[3]/td[3]/div/div/input"),
                driver, 5);
        if (!input.equals(size.getText())) {
            action.sendKeys(size, Keys.BACK_SPACE, "a").sendKeys(size, input).build().perform();
            Thread.sleep(1000);
        }
    }

    public static void sendRequest(WebDriver driver, Actions action) throws InterruptedException {
        WebElement sendButton = SpiderUtil.getElementUntil(
                By.xpath("//*[@id=\"app\"]/section/section/main/div/div[2]/div/form/div[4]/div/button"), driver, 5);
        action.click(sendButton).build().perform();
        Thread.sleep(4000);
    }

    public static HuoBiKline getData(WebDriver driver) {
        WebElement responseArea = SpiderUtil.getElementUntil(
                By.xpath("//*[@id=\"app\"]/section/section/main/div/div[2]/div/form/div[5]/div/div/textarea"), driver,
                5);
        String attribute = responseArea.getAttribute("value").replace("\n", "").replace(" ", "");
        return JSON.parseObject(attribute, HuoBiKline.class);
    }


    public static WebDriver driverBuilderChromeHeadLess() {
        // chrome
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        // headless open driver
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.setBinary("/opt/google/chrome/google-chrome");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("no-sandbox");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("window-size=1920,720");
        // chromeOptions.addArguments("window-size=1024,768");
        return new ChromeDriver(chromeOptions);
    }

    public static String stampToDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }

    public static void buyDuo50Percent(Integer i) {
        int use = duoRepository / 2;
        duoRepository -= use;
        duoCount += use;
        // if (use > 0) {
        // double p = DataInfo.PRICE[i];
        // // sum =
        // sum.subtract(BigDecimal.valueOf(p).multiply(BigDecimal.valueOf(use)));
        // }
    }

    public static void sellDuoAll(Integer i) {
        duoRepository += duoCount;
        int use = duoCount;
        duoCount = 0;
        // if (use > 0) {
        // double p = DataInfo.PRICE[i];
        // // sum = sum.add(BigDecimal.valueOf(p).multiply(BigDecimal.valueOf(use)));
        // }
    }

    public static void buyKong50Percent(Integer i) {
        int use = kongRepository / 2;
        kongRepository -= use;
        kongCount += use;
        // if (use > 0) {
        // double p = DataInfo.PRICE[i];
        // // sum =
        // sum.subtract(BigDecimal.valueOf(p).multiply(BigDecimal.valueOf(use)));
        // }
    }

    public static void sellKongAll(Integer i) {
        kongRepository += kongCount;
        int use = kongCount;
        kongCount = 0;
        // if (use > 0) {
        // double p = DataInfo.PRICE[i];
        // // sum = sum.add(BigDecimal.valueOf(p).multiply(BigDecimal.valueOf(use)));
        // }
    }
}
