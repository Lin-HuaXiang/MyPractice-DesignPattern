package com.example.designpatternspider.selenium.huobi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.designpatternspider.selenium.huobi.po.Data;
import com.example.designpatternspider.selenium.huobi.po.HuoBiKline;
import com.example.designpatternspider.selenium.util.SpiderUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HuoBiOpenSpider {

    public static void main(String[] args) throws Exception {
        WebDriver driver = driverBuilderChromeHeadLess();
        Actions action = new Actions(driver);

        SpiderUtil.requestData("https://open.huobigroup.com/?name=kline", driver);

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

        for (int i = 0; i < n; i++) {

            sendRequest(driver, action);
            HuoBiKline kline72 = getData(driver);

            BigDecimal calcRsi9 = calcRsi(kline72.getData().subList(0, 9));
            BigDecimal calcRsi12 = calcRsi(kline72.getData().subList(0, 12));
            BigDecimal calcRsi14 = calcRsi(kline72.getData().subList(0, 14));
            BigDecimal calcRsi72 = calcRsi(kline72.getData());

            log.info("[{}], [[{}], {}, {}, {}]", kline72.getData().get(0).getClose(), calcRsi14, calcRsi9, calcRsi12, calcRsi72);

            Calendar calendar = Calendar.getInstance();
            int second = calendar.get(Calendar.SECOND);
            Thread.sleep((60 - second) * 1000);

        }

        driver.quit();
    }

    public static void inputSymbol(WebDriver driver, Actions action, String input) throws InterruptedException {
        WebElement symbol = SpiderUtil.getElementUntil(By.xpath(
                "//*[@id=\"app\"]/section/section/main/div/div[1]/div/div[8]/div[1]/div[3]/table/tbody/tr[1]/td[3]/div/div/input"),
                driver, 5);
        action.sendKeys(symbol, Keys.CONTROL, "a").sendKeys(symbol, input).build().perform();
        Thread.sleep(1000);
    }

    public static void inputPeriod(WebDriver driver, Actions action, String input) throws InterruptedException {
        // support 1min, 5min, 15min, 30min, 60min, 4hour, 1day, 1mon, 1week, 1year
        WebElement period = SpiderUtil.getElementUntil(By.xpath(
                "//*[@id=\"app\"]/section/section/main/div/div[1]/div/div[8]/div[1]/div[3]/table/tbody/tr[2]/td[3]/div/div/input"),
                driver, 5);
        action.sendKeys(period, Keys.BACK_SPACE, "a").sendKeys(period, input).build().perform();
        Thread.sleep(1000);
    }

    public static void inputSize(WebDriver driver, Actions action, String input) throws InterruptedException {
        // support [1, 2000]
        WebElement size = SpiderUtil.getElementUntil(By.xpath(
                "//*[@id=\"app\"]/section/section/main/div/div[1]/div/div[8]/div[1]/div[3]/table/tbody/tr[3]/td[3]/div/div/input"),
                driver, 5);
        action.sendKeys(size, Keys.BACK_SPACE, "a").sendKeys(size, input).build().perform();
        Thread.sleep(1000);
    }

    public static void sendRequest(WebDriver driver, Actions action) throws InterruptedException {
        WebElement sendButton = SpiderUtil.getElementUntil(
                By.xpath("//*[@id=\"app\"]/section/section/main/div/div[2]/div/form/div[4]/div/button"), driver, 5);
        action.click(sendButton).build().perform();
        Thread.sleep(1000);
    }

    public static HuoBiKline getData(WebDriver driver) {
        WebElement responseArea = SpiderUtil.getElementUntil(
                By.xpath("//*[@id=\"app\"]/section/section/main/div/div[2]/div/form/div[5]/div/div/textarea"), driver,
                5);
        String attribute = responseArea.getAttribute("value").replace("\n", "").replace(" ", "");
        return JSON.parseObject(attribute, HuoBiKline.class);
    }

    public static BigDecimal calcRsi(List<Data> dataList) {
        BigDecimal upSum = BigDecimal.ZERO;
        BigDecimal downSum = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(dataList)) {
            // RSI=100-[100/(1+RS)]
            for (Data data : dataList) {
                BigDecimal subtract = data.getClose().subtract(data.getOpen());
                if (subtract.compareTo(BigDecimal.ZERO) > 0) {
                    // 上升数值
                    upSum = upSum.add(subtract);
                } else if (subtract.compareTo(BigDecimal.ZERO) < 0) {
                    // 下降数值
                    downSum = downSum.add(subtract.abs());
                }
            }
            int len = dataList.size();
            return calcRsi(upSum, downSum, len);
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal calcRsi(BigDecimal upSum, BigDecimal downSum, int size) {
        // log.info("up sum {}", upSum);
        // log.info("down sum {}", downSum);
        // up Sum
        BigDecimal upRs = upSum.divide(BigDecimal.valueOf(size), 3, RoundingMode.UP);
        // log.info("up sum avg {}", upRs);
        // down Sum
        BigDecimal downRs = downSum.divide(BigDecimal.valueOf(size), 3, RoundingMode.UP);
        // log.info("down sum avg {}", downRs);
        // RS
        BigDecimal rs = upRs.divide(downRs, 3, RoundingMode.UP);
        // log.info("RS {}", rs);
        // 1+RS
        BigDecimal onePlueRs = BigDecimal.valueOf(1).add(rs);
        // log.info("1+RS {}", onePlueRs);
        // 100/(1+RS)
        BigDecimal hundredDev = BigDecimal.valueOf(100).divide(onePlueRs, 3, RoundingMode.UP);
        // log.info("100/(1+RS) {}", hundredDev);
        // 100-[100/(1+RS)]
        BigDecimal res = BigDecimal.valueOf(100).subtract(hundredDev);
        // log.info("100-[100/(1+RS)] {}", res);
        return res;
    }

    public static WebDriver driverBuilderChromeHeadLess() {
        // 谷歌浏览器
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        // 无头浏览器打开
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.setBinary("/opt/google/chrome/google-chrome");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        // chromeOptions.addArguments("window-size=1920,1080");
        chromeOptions.addArguments("window-size=1024,768");
        return new ChromeDriver(chromeOptions);
    }

    public static String stampToDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }

}
