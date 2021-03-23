package com.example.designpatternspider.selenium.huobi;


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
public class HuoBiOpenSpider {

    public static void main(String[] args) throws Exception {
        WebDriver driver = driverBuilderChromeHeadLess();
        Actions action = new Actions(driver);

        SpiderUtil.requestData("https://open.huobigroup.com/?name=kline", driver);

        WebElement symbol = SpiderUtil.getElementUntil(By.xpath("//*[@id=\"app\"]/section/section/main/div/div[1]/div/div[8]/div[1]/div[3]/table/tbody/tr[1]/td[3]/div/div/input"), driver, 5);
        action.sendKeys(symbol, Keys.CONTROL, "a").sendKeys(symbol, "filusdt").build().perform();
        Thread.sleep(1000);
        WebElement size = SpiderUtil.getElementUntil(By.xpath("//*[@id=\"app\"]/section/section/main/div/div[1]/div/div[8]/div[1]/div[3]/table/tbody/tr[3]/td[3]/div/div/input"), driver, 5);
        action.sendKeys(size, Keys.BACK_SPACE, "a").sendKeys(size, "1").build().perform();
        Thread.sleep(1000);

        for (int i = 0; i < 1440; i++) {

            WebElement sendButton = SpiderUtil.getElementUntil(By.xpath("//*[@id=\"app\"]/section/section/main/div/div[2]/div/form/div[4]/div/button"), driver, 5);
            action.click(sendButton).build().perform();

            Thread.sleep(2000);
            WebElement responseArea = SpiderUtil.getElementUntil(By.xpath("//*[@id=\"app\"]/section/section/main/div/div[2]/div/form/div[5]/div/div/textarea"), driver, 5);
            log.info("{}", responseArea.getAttribute("value").replace("\n", "").replace(" ", ""));

            Thread.sleep(60*1000);

        }
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

    


}
