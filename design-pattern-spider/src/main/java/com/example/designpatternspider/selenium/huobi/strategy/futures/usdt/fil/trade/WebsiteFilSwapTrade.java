package com.example.designpatternspider.selenium.huobi.strategy.futures.usdt.fil.trade;

import java.math.BigDecimal;

import com.example.designpatternspider.selenium.exception.MyException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsiteFilSwapTrade implements IFilSwapTrade {

    @Override
    public void openLong(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        try {
            log.info("[open long] Click open long tag...");
            String cssOpen = "#root > div:nth-child(1) > div.linear-swap-trade > div.linear-swap-outer-right-wrap > div.linear-swap-inner-left-wrap > div.linear-swap-board.content-box > div.trade-content-box-title.linear-swap-board-title.swap-board-blue-title > div.linear-swap-board-open-close > h2:nth-child(1)";
            WebElement eOpen = driver.findElement(By.cssSelector(cssOpen));
            action.click(eOpen).build().perform();
            Thread.sleep(1000);

            log.info("[open long] Click to select gear...");
            String cssGear = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[1]/div[2]/div/span[2]";
            WebElement eGear = driver.findElement(By.xpath(cssGear));
            action.click(eGear).moveToElement(eGear).build().perform();
            Thread.sleep(1000);
            
            log.info("[open long] Click to select gear five...");
            String cssGearFive = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[1]/div[2]/ul/li[2]/span";
            WebElement eGearFive = driver.findElement(By.xpath(cssGearFive));
            action.click(eGearFive).build().perform();
            Thread.sleep(1000);

            log.info("[open long] Buy 50% notes...");
            String cssByForty = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[3]/div/div/i[4]";
            WebElement eByForty = driver.findElement(By.xpath(cssByForty));
            action.click(eByForty).build().perform();
            Thread.sleep(1000);

            log.info("[open long] open submit..");
            String cssSubmit = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/button";
            WebElement eSubmit = driver.findElement(By.xpath(cssSubmit));
            action.click(eSubmit).build().perform();
            Thread.sleep(1000);

            log.info("[open long] open finished..");
        } catch (Exception e) {
            log.error("website file wap open long error", e);
            throw new MyException("website file wap open long error", e);
        }
    }

    @Override
    public void openShort(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        try {
            log.info("[open short] Click open short tag...");
            String cssOpen = "#root > div:nth-child(1) > div.linear-swap-trade > div.linear-swap-outer-right-wrap > div.linear-swap-inner-left-wrap > div.linear-swap-board.content-box > div.trade-content-box-title.linear-swap-board-title.swap-board-blue-title > div.linear-swap-board-open-close > h2:nth-child(1)";
            WebElement eOpen = driver.findElement(By.cssSelector(cssOpen));
            action.click(eOpen).build().perform();
            Thread.sleep(1000);

            log.info("[open short] Click to select gear...");
            String cssGear = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[1]/div[1]/div[2]/div/span[2]";
            WebElement eGear = driver.findElement(By.xpath(cssGear));
            action.click(eGear).moveToElement(eGear).build().perform();
            Thread.sleep(1000);

            log.info("[open short] Click to select gear five...");
            String cssGearFive = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[1]/div[1]/div[2]/ul/li[2]/span";
            WebElement eGearFive = driver.findElement(By.xpath(cssGearFive));
            action.click(eGearFive).build().perform();
            Thread.sleep(1000);

            log.info("[open short] Buy 50% notes..");
            String cssByForty = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[3]/div/div/i[4]";
            WebElement eByForty = driver.findElement(By.xpath(cssByForty));
            action.click(eByForty).build().perform();
            Thread.sleep(1000);

            log.info("[open short] open submit..");
            String cssSubmit = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/button";
            WebElement eSubmit = driver.findElement(By.xpath(cssSubmit));
            action.click(eSubmit).build().perform();
            Thread.sleep(1000);

            log.info("[open short] buy finished..");
        } catch (Exception e) {
            log.error("website file wap open short error", e);
            throw new MyException("website file wap open short error", e);
        }

    }

    @Override
    public void closeLong(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        try {
            log.info("[close long] Click close long tag...");
            String cssOpen = "#root > div:nth-child(1) > div.linear-swap-trade > div.linear-swap-outer-right-wrap > div.linear-swap-inner-left-wrap > div.linear-swap-board.content-box > div.trade-content-box-title.linear-swap-board-title.swap-board-blue-title > div.linear-swap-board-open-close > h2:nth-child(2)";
            WebElement eOpen = driver.findElement(By.cssSelector(cssOpen));
            action.click(eOpen).build().perform();
            Thread.sleep(1000);

            log.info("[close long] Click to quick close gear...");
            String cssFlash = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[1]/div[1]/div[3]";
            WebElement eFlash = driver.findElement(By.xpath(cssFlash));
            action.click(eFlash).build().perform();
            Thread.sleep(1000);

            // 100%卖出
            log.info("[close long] Sell 100% notes...");
            String cssSellHundred = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[3]/div/div/i[6]";
            WebElement eSellHundred = driver.findElement(By.xpath(cssSellHundred));
            action.click(eSellHundred).build().perform();
            Thread.sleep(1000);

            // 提交
            log.info("[close long] close submit...");
            String cssSubmit = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/button";
            WebElement eSubmit = driver.findElement(By.xpath(cssSubmit));
            action.click(eSubmit).build().perform();
            Thread.sleep(1000);

            log.info("[close long] close finished...");
        } catch (Exception e) {
            log.error("website file wap close long error", e);
            throw new MyException("website file wap close long error", e);
        }
    }

    @Override
    public void closeShort(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws Exception {
        try {
            log.info("[close short] Click close short tag...");
            String cssOpen = "#root > div:nth-child(1) > div.linear-swap-trade > div.linear-swap-outer-right-wrap > div.linear-swap-inner-left-wrap > div.linear-swap-board.content-box > div.trade-content-box-title.linear-swap-board-title.swap-board-blue-title > div.linear-swap-board-open-close > h2:nth-child(2)";
            WebElement eOpen = driver.findElement(By.cssSelector(cssOpen));
            action.click(eOpen).build().perform();
            Thread.sleep(1000);

            log.info("[close short] Click to quick close gear...");
            String cssFlash = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[1]/div[3]";
            WebElement eFlash = driver.findElement(By.xpath(cssFlash));
            action.click(eFlash).build().perform();
            Thread.sleep(1000);

            log.info("[close short] Sell 100% notes...");
            String cssSellHundred = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[3]/div/div/i[6]";
            WebElement eSellHundred = driver.findElement(By.xpath(cssSellHundred));
            action.click(eSellHundred).build().perform();
            Thread.sleep(1000);

            // 提交
            log.info("[close short] close submit...");
            String cssSubmit = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/button";
            WebElement eSubmit = driver.findElement(By.xpath(cssSubmit));
            action.click(eSubmit).build().perform();
            Thread.sleep(1000);

            log.info("[close short] close finished...");
        } catch (Exception e) {
            log.error("website file wap close short error", e);
            throw new MyException("website file wap close short error", e);
        }
    }

    @Override
    public BigDecimal getTotalEquity(ChromeDriver driver, BigDecimal price) throws Exception {
        // TODO implement
        return null;
    }

}
