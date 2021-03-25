package com.example.designpatternspider.selenium.huobi.api;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.designpatternspider.selenium.util.SpiderUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HuobiLinearSwapSpider {

    public static final String KLINE_URL = "https://futures.huobi.be/zh-cn/linear_swap/exchange/#contract_code=FIL-USDT&type=isolated";

    public static void openProfession(WebDriver driver, WebDriverWait driverWait, Actions action)
            throws InterruptedException {
        String selectMinute = "#chartOuterWrap > div.k-chart-head.content-box.border-split > div.switch-operate > h2.btn-selected:nth-child(2)";
        WebElement eSelect = SpiderUtil.getElementUntil(By.cssSelector(selectMinute), driver, 3);
        if (eSelect != null) {
            return;
        }
        String cssProfession = "#chartOuterWrap > div.k-chart-head.content-box.border-split > div.switch-operate > h2:nth-child(2)";
        WebElement eProfession = SpiderUtil.getElementUntil(By.cssSelector(cssProfession), driver, 3);
        log.info("open profession graph, {}", eProfession.getText());
        action.click(eProfession).build().perform();
        Thread.sleep(1000);
    }

    public static void openMinute(WebDriver driver, WebDriverWait driverWait, Actions action)
            throws InterruptedException {
        String selectMinute = "#chartOuterWrap > div.k-chart-head.content-box.border-split > div.chart-operate > div.min-screen.secondary-font > button:nth-child(3).selected";
        WebElement eSelect = SpiderUtil.getElementUntil(By.cssSelector(selectMinute), driver, 3);
        if (eSelect != null) {
            return;
        }
        String cssMinute = "#chartOuterWrap > div.k-chart-head.content-box.border-split > div.chart-operate > div.min-screen.secondary-font > button:nth-child(3)";
        WebElement eMinute = SpiderUtil.getElementUntil(By.cssSelector(cssMinute), driver, 3);
        log.info("click 1 minute graph, {}", eMinute.getText());
        action.click(eMinute).build().perform();
        Thread.sleep(1000);
    }

    public static void openMACD(WebDriver driver, WebDriverWait driverWait, Actions action)
            throws InterruptedException {
        // 判断是否已经打开MACD线
        WebElement eIframe = SpiderUtil.getElementUntil(By.cssSelector("#kChartProfession > iframe"), driver, 5);
        driver.switchTo().frame(eIframe);
        String cssIsOpen = "/html/body/div[1]/div[1]/div/div[1]/div[2]/table/tbody/tr[5]/td[2]/div/div[3]/div/span[1]";
        WebElement eIsOpen = SpiderUtil.getElementUntil(By.xpath(cssIsOpen), driver, 3);
        if (eIsOpen != null && eIsOpen.getText().contains("MACD")) {
            return;
        }

        driver.switchTo().defaultContent();

        log.info("start open macd graph");
        // 打开指标面板
        log.info("open index bar");
        String cssIndex = "//*[@id=\"chartOuterWrap\"]/div[1]/div[1]/div[2]/button[2]";
        WebElement eIndex = SpiderUtil.getElementUntil(By.xpath(cssIndex), driver, 3);
        action.click(eIndex).build().perform();
        Thread.sleep(1000);

        // 选择MACD线
        log.info("select macd index");
        driver.switchTo().frame(eIframe);
        WebElement eMACD = SpiderUtil
                .getElementUntil(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div[3]/div/div[2]/div"), driver, 3);
        action.click(eMACD).build().perform();
        Thread.sleep(1000);

        driver.switchTo().defaultContent();

        // 关闭指标面板
        log.info("close index bar");
        eIndex = SpiderUtil.getElementUntil(By.xpath(cssIndex), driver, 3);
        action.click(eIndex).build().perform();
        Thread.sleep(1000);

        log.info("finished open macd graph");

    }

    public static void closeAd(WebDriver driver, WebDriverWait driverWait, Actions action) throws InterruptedException {
        String iKnow = "div.bottom-main > div";
        List<WebElement> iKnowES = SpiderUtil.getElementsUntil(By.cssSelector(iKnow), driver, 3);
        iKnowES = Optional.ofNullable(iKnowES).orElse(Collections.emptyList());
        for (WebElement webElement : iKnowES) {
            String text = webElement.getText();
            if ("我知道了".equals(text)) {
                log.info("close ad i know, {}", text);
                action.click(webElement).build().perform();
            }
        }
        String close = "i.icon-info-close";
        List<WebElement> closeES = SpiderUtil.getElementsUntil(By.cssSelector(close), driver, 3);
        closeES = Optional.ofNullable(closeES).orElse(Collections.emptyList());
        for (WebElement webElement : closeES) {
            log.info("close ad close, {}", webElement.getText());
            action.click(webElement).build().perform();
        }
    }

    public static void refreshMacd(WebDriver driver, WebDriverWait driverWait, Actions action)
            throws InterruptedException {
        // 鼠标移动到面板，避免数据没有刷新
        String cssMacdBarLeft = "/html/body/div[1]/div[1]/div/div[1]/div[2]/div[2]/div/div[2]/div[1]";
        WebElement eMacdBarLeft = SpiderUtil.getElementUntil(By.xpath(cssMacdBarLeft), driver, 3);
        action.click(eMacdBarLeft).build().perform();
        String cssMacdBarReset = "/html/body/div[1]/div[1]/div/div[1]/div[2]/div[2]/div/div[3]";
        WebElement eMacdBarReset = SpiderUtil.getElementUntil(By.xpath(cssMacdBarReset), driver, 3);
        action.click(eMacdBarReset).build().perform();
        Thread.sleep(1000);
    }

    public static BigDecimal getMacdDif(WebDriver driver, WebDriverWait driverWait, Actions action) {
        String cssMac = "/html/body/div[1]/div[1]/div/div[1]/div[2]/table/tbody/tr[5]/td[2]/div/div[3]/div/div/span[2]/span";
        WebElement eMac = SpiderUtil.getElementUntil(By.xpath(cssMac), driver, 3);
        return new BigDecimal(eMac.getText());
    }

    public static BigDecimal getMacd(WebDriver driver, WebDriverWait driverWait, Actions action) {
        String cssDev = "/html/body/div[1]/div[1]/div/div[1]/div[2]/table/tbody/tr[5]/td[2]/div/div[3]/div/div/span[1]/span";
        WebElement eDev = SpiderUtil.getElementUntil(By.xpath(cssDev), driver, 3);
        return new BigDecimal(eDev.getText());

    }

    public static BigDecimal getPrice(WebDriver driver, WebDriverWait driverWait, Actions action) {
        // price charge
        String css1 = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/span[1]";
        WebElement e1 = SpiderUtil.getElementUntil(By.xpath(css1), driver, 3);
        String priceString = e1.getText();
        return new BigDecimal(priceString);
    }

    public static void switchToKChart(WebDriver driver, WebDriverWait driverWait, Actions action) {
        // get k chart profession iframe
        WebElement eIframe = SpiderUtil.getElementUntil(By.cssSelector("#kChartProfession > iframe"), driver, 3);
        driver.switchTo().frame(eIframe);
    }
}
