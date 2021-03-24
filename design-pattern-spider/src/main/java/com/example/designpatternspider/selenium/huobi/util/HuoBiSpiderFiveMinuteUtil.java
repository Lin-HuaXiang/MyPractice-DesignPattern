package com.example.designpatternspider.selenium.huobi.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class HuoBiSpiderFiveMinuteUtil {

    public static void main(String[] args) throws InterruptedException {
        // 配置驱动
        System.setProperty("webdriver.chrome.driver", "F:\\soft\\chromedriver_win32\\chromedriver.exe");
        // 创建ChromeDriver对象
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-gpu");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        ChromeDriver driver = new ChromeDriver(options);
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        Actions action = new Actions(driver);

        // 选择货币
        String currency = "FIL-USDT";
        // String currency = "CHZ-USDT";

        // 打开网站
        driver.get("https://futures.huobi.be/zh-cn/linear_swap/exchange/#contract_code=" + currency + "&type=isolated");

        log.info("login prepare {}", 30);
        Thread.sleep(30 * 1000);

        // 修正分钟准点
        BigDecimal lastPrice = new BigDecimal("0");
        Calendar c1 = Calendar.getInstance(); // 当前日期
        int senc = c1.get(Calendar.SECOND);
        int intervalSenc = 60 - senc;
//        int minu = c1.get(Calendar.MINUTE);
//        int intervalMinu = 5 - 1 - minu % 5;
//        log.info("time fix {}", ((intervalMinu * 60) + intervalSenc));
//        Thread.sleep(((intervalMinu * 60) + intervalSenc) * 1000);
        Thread.sleep(intervalSenc * 1000);

        String sig = "null";
        String lastSig = "null";
        String lastLastSig = "null";
        BigDecimal lastMac = BigDecimal.ZERO;
        BigDecimal lastDev = BigDecimal.ZERO;
        BigDecimal lastSubDev = BigDecimal.ZERO;
        BigDecimal lastSubMac = BigDecimal.ZERO;
        Integer reset = 1;
        Boolean continuityDuo = false;
        Boolean continuityKong = false;
        for (int i = 0; i < 1440; i++) {
            // 每5次错误，重置网站
            if (reset % 5 == 0) {
                reset++;
                driver.get("https://futures.huobi.be/zh-cn/linear_swap/exchange/#contract_code=" + currency + "&type=isolated");
            }
            try {
                // 关闭广告
                closeAd(driver, driverWait, action);
                // 打开专业版
                openProfession(driver, driverWait, action);
                // 打开分钟线
                openMinute(driver, driverWait, action);
                // 打开MACD线
                openMACD(driver, driverWait, action);
                driver.switchTo().defaultContent();
                // price charge
                String css1 = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/span[1]";
                WebElement e1 = driver.findElement(By.xpath(css1));
                String priceString = e1.getText();
                BigDecimal price = new BigDecimal(priceString);

                WebElement eIframe = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kChartProfession > iframe")));
                driver.switchTo().frame(eIframe);

                // 鼠标移动到面板，避免数据没有刷新
                String cssMacdBarLeft = "/html/body/div[1]/div[1]/div/div[1]/div[2]/div[2]/div/div[2]/div[1]";
                WebElement eMacdBarLeft = driver.findElement(By.xpath(cssMacdBarLeft));
                action.click(eMacdBarLeft).build().perform();
                String cssMacdBarReset = "/html/body/div[1]/div[1]/div/div[1]/div[2]/div[2]/div/div[3]";
                WebElement eMacdBarReset = driver.findElement(By.xpath(cssMacdBarReset));
                action.click(eMacdBarReset).build().perform();
                Thread.sleep(1000);

                String cssMac = "/html/body/div[1]/div[1]/div/div[1]/div[2]/table/tbody/tr[5]/td[2]/div/div[3]/div/div/span[2]/span";
                WebElement eMac = driver.findElement(By.xpath(cssMac));
                BigDecimal mac = new BigDecimal(eMac.getText());

                String cssDev = "/html/body/div[1]/div[1]/div/div[1]/div[2]/table/tbody/tr[5]/td[2]/div/div[3]/div/div/span[1]/span";
                WebElement eDev = driver.findElement(By.xpath(cssDev));
                BigDecimal dev = new BigDecimal(eDev.getText());

                driver.switchTo().defaultContent();

                BigDecimal subDev = dev.subtract(lastDev);
                BigDecimal subPrice = price.subtract(lastPrice);
                BigDecimal subMac = mac.subtract(lastMac);


                if (i == 0) {
                    lastDev = dev;
                    lastPrice = price;
                    lastMac = mac;
                }

                c1 = Calendar.getInstance(); // 当前日期
                senc = c1.get(Calendar.MINUTE);

                if (senc % 5 != 0) {
                    // up channel
                    if (dev.compareTo(BigDecimal.ZERO) > 0) {
                        if (dev.compareTo(lastDev) < 0 || mac.compareTo(lastMac) < 0) {
                            log.info("提前感知 上行通道 趋势变差");
                            sellDuo(driver, driverWait, action);
                        }
                    }
                    // down channel
                    if (dev.compareTo(BigDecimal.ZERO) < 0) {
                        if (dev.compareTo(lastDev) > 0 || mac.compareTo(lastMac) > 0) {
                            log.info("提前感知 下行通道 趋势变差");
                            sellKong(driver, driverWait, action);
                        }
                    }
                    // 提前感知是否存在dev切换
                    c1 = Calendar.getInstance(); // 当前日期
                    senc = c1.get(Calendar.SECOND);
                    intervalSenc = 60 - senc;
                    Thread.sleep(intervalSenc * 1000);
                    continue;
                }



                if (mac.compareTo(lastMac) > 0) {
                    sig = "up";
                    continuityKong = false;
                } else if (mac.compareTo(lastMac) == 0) {
                    sig = "wait";
                } else {
                    sig = "down";
                    continuityDuo = false;
                }

                if (dev.compareTo(BigDecimal.ZERO) >= 0 && lastDev.compareTo(BigDecimal.ZERO) <= 0) {
                    log.info("金叉买多");
                    sellKong(driver, driverWait, action);
                    buyDuo(driver, driverWait, action);
                }
                // If the deviation changes from a positive number to a negative number, then
                // buy kong
                if (dev.compareTo(BigDecimal.ZERO) <= 0 && lastDev.compareTo(BigDecimal.ZERO) >= 0) {
                    log.info("死叉买空");
                    sellDuo(driver, driverWait, action);
                    buyKong(driver, driverWait, action);
                }


                // up channel
                if (dev.compareTo(BigDecimal.ZERO) > 0) {

                    // if the deviation ratio is relatively small, then ignore; 0~10 ignore 30~max
                    // if two consecutive deviations decrease, then sell;
                    // sell
                    if (dev.compareTo(lastDev) < 0 || mac.compareTo(lastMac) < 0) {
                        log.info("上行通道 趋势变差");
                        sellDuo(driver, driverWait, action);
//                        buyKong(driver, driverWait, action);
                    }

                    if ((lastSubDev.compareTo(BigDecimal.ZERO) <= 0 && subDev.compareTo(BigDecimal.ZERO) > 0)
                            || (lastSubMac.compareTo(BigDecimal.ZERO) <= 0 && subMac.compareTo(BigDecimal.ZERO) > 0)) {
                        log.info("上行通道 趋势转好");
                        sellKong(driver, driverWait, action);
                        buyDuo(driver, driverWait, action);
                    }

                }

                // down channel
                if (dev.compareTo(BigDecimal.ZERO) < 0) {

                    if (dev.compareTo(lastDev) > 0 || mac.compareTo(lastMac) > 0) {
                        log.info("下行通道 趋势变差");
                        sellKong(driver, driverWait, action);
//                        buyDuo(driver, driverWait, action);
                    }

                    if ((lastSubDev.compareTo(BigDecimal.ZERO) >= 0 && subDev.compareTo(BigDecimal.ZERO) < 0)
                            || (lastSubMac.compareTo(BigDecimal.ZERO) >= 0 && subMac.compareTo(BigDecimal.ZERO) < 0)) {
                        log.info("下行通道 趋势转好");
                        sellDuo(driver, driverWait, action);
                        buyKong(driver, driverWait, action);
                    }
                }


                // 先打印
                log.info("{}, {}, {}, {}, {}, {}", price, mac, dev, lastLastSig, lastSig, sig);
                lastLastSig = lastSig;
                lastPrice = price;
                lastSig = sig;
                lastMac = mac;
                lastDev = dev;
                lastSubDev = subDev;
                lastSubMac = subMac;

                c1 = Calendar.getInstance(); // 当前日期
                senc = c1.get(Calendar.SECOND);
                intervalSenc = 60 - senc;
//                minu = c1.get(Calendar.MINUTE);
//                intervalMinu = 5 - 1 - minu % 5;
//                Thread.sleep(((intervalMinu * 60) + intervalSenc) * 1000);
                Thread.sleep(intervalSenc * 1000);
            } catch (Exception e) {
                reset++;
                driver.switchTo().defaultContent();
                log.error("第{}次获取数据，失败了", i + 1, e);
                // 异常情况，不能等待太久
                Thread.sleep(30 * 1000);
            }
//            c1 = Calendar.getInstance(); // 当前日期
//            s1 = c1.get(Calendar.SECOND);
//            interval = 300 - s1;
//            // interval = interval > 30 ? interval - 30 : interval;
//            Thread.sleep(interval * 1000);

        }

        driver.quit();
    }

    private static void openProfession(ChromeDriver driver, WebDriverWait driverWait, Actions action) throws InterruptedException {
        String selectMinute = "#chartOuterWrap > div.k-chart-head.content-box.border-split > div.switch-operate > h2.btn-selected:nth-child(2)";
        WebElement eSelect = null;
        try {
            eSelect = driver.findElement(By.cssSelector(selectMinute));
        } catch (Exception e) {
        }
        if (eSelect != null) {
            return;
        }
        String cssProfession = "#chartOuterWrap > div.k-chart-head.content-box.border-split > div.switch-operate > h2:nth-child(2)";
        WebElement eProfession = driver.findElement(By.cssSelector(cssProfession));
        log.info("open profession graph, {}", eProfession.getText());
        action.click(eProfession).build().perform();
        Thread.sleep(1000);
    }

    public static void openMinute(WebDriver driver, WebDriverWait driverWait, Actions action) throws InterruptedException {
        String selectMinute = "#chartOuterWrap > div.k-chart-head.content-box.border-split > div.chart-operate > div.min-screen.secondary-font > button:nth-child(3).selected";
        WebElement eSelect = null;
        try {
            eSelect = driver.findElement(By.cssSelector(selectMinute));
        } catch (Exception e) {
        }
        if (eSelect != null) {
            return;
        }
        String cssMinute = "#chartOuterWrap > div.k-chart-head.content-box.border-split > div.chart-operate > div.min-screen.secondary-font > button:nth-child(3)";
        WebElement eMinute = driver.findElement(By.cssSelector(cssMinute));
        log.info("click 1 minute graph, {}", eMinute.getText());
        action.click(eMinute).build().perform();
        Thread.sleep(1000);
    }

    public static void openMACD(WebDriver driver, WebDriverWait driverWait, Actions action)
            throws InterruptedException {
        // 判断是否已经打开MACD线
        WebElement eIframe = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kChartProfession > iframe")));
        driver.switchTo().frame(eIframe);
        String cssIsOpen = "/html/body/div[1]/div[1]/div/div[1]/div[2]/table/tbody/tr[5]/td[2]/div/div[3]/div/span[1]";
        WebElement eIsOpen = null;
        try {
            eIsOpen = driver.findElement(By.xpath(cssIsOpen));
        } catch (Exception e) {
        }
        if (eIsOpen != null && eIsOpen.getText().contains("MACD")) {
            driver.switchTo().defaultContent();
            return;
        }
        driver.switchTo().defaultContent();

        log.info("start open macd graph");
        // 打开指标面板
        log.info("open index bar");
        String cssIndex = "//*[@id=\"chartOuterWrap\"]/div[1]/div[1]/div[2]/button[2]";
        WebElement eIndex = driver.findElement(By.xpath(cssIndex));
        action.click(eIndex).build().perform();
        Thread.sleep(1000);

        // 选择MACD线
        log.info("select macd index");
        driver.switchTo().frame(eIframe);
        WebElement eMACD = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div[3]/div/div[2]/div"));
        action.click(eMACD).build().perform();
        Thread.sleep(1000);

        driver.switchTo().defaultContent();

        // 关闭指标面板
        log.info("close index bar");
        eIndex = driver.findElement(By.xpath(cssIndex));
        action.click(eIndex).build().perform();
        Thread.sleep(1000);

        log.info("finished open macd graph");
    }

    public static void closeAd(WebDriver driver, WebDriverWait driverWait, Actions action) throws InterruptedException {
        String iKnow = "div.bottom-main > div";
        List<WebElement> iKnowES = driver.findElements(By.cssSelector(iKnow));
        iKnowES = Optional.ofNullable(iKnowES).orElse(Collections.emptyList());
        for (WebElement webElement : iKnowES) {
            String text = webElement.getText();
            if ("我知道了".equals(text)) {
                log.info("close ad i know, {}", text);
                action.click(webElement).build().perform();
            }
        }
        String close = "i.icon-info-close";
        List<WebElement> closeES = driver.findElements(By.cssSelector(close));
        closeES = Optional.ofNullable(closeES).orElse(Collections.emptyList());
        for (WebElement webElement : closeES) {
            log.info("close ad close, {}", webElement.getText());
            action.click(webElement).build().perform();
        }

    }

    public static void buyDuo(WebDriver driver, WebDriverWait driverWait, Actions action) throws InterruptedException {
        // 点击开仓
        log.info("[buy duo] open position...");
        String cssOpen = "#root > div:nth-child(1) > div.linear-swap-trade > div.linear-swap-outer-right-wrap > div.linear-swap-inner-left-wrap > div.linear-swap-board.content-box > div.trade-content-box-title.linear-swap-board-title.swap-board-blue-title > div.linear-swap-board-open-close > h2:nth-child(1)";
        WebElement eOpen = driver.findElement(By.cssSelector(cssOpen));
        action.click(eOpen).build().perform();
        Thread.sleep(1000);
        // 点击选择挡位
        log.info("[buy duo] choose gear...");
        String cssGear = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[1]/div[2]/div/span[2]";
        WebElement eGear = driver.findElement(By.xpath(cssGear));
        action.click(eGear).moveToElement(eGear).build().perform();
        Thread.sleep(1000);
        // 最优五档
        log.info("[buy duo] choose gear five...");
        String cssGearFive = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[1]/div[2]/ul/li[2]/span";
        WebElement eGearFive = driver.findElement(By.xpath(cssGearFive));
        action.click(eGearFive).build().perform();
        Thread.sleep(1000);
        // 50%买入
        log.info("[buy duo] buy fifty..");
        String cssByForty = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[3]/div/div/i[4]";
        WebElement eByForty = driver.findElement(By.xpath(cssByForty));
        action.click(eByForty).build().perform();
        Thread.sleep(1000);
        // 提交
        log.info("[buy duo] buy submit..");
        String cssSubmit = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/button";
        WebElement eSubmit = driver.findElement(By.xpath(cssSubmit));
        action.click(eSubmit).build().perform();
        Thread.sleep(1000);
        log.info("[buy duo] buy finished..");
    }

    public static void sellDuo(WebDriver driver, WebDriverWait driverWait, Actions action) throws InterruptedException {
        // 点击平仓
        log.info("[sell duo] liquidate position...");
        String cssOpen = "#root > div:nth-child(1) > div.linear-swap-trade > div.linear-swap-outer-right-wrap > div.linear-swap-inner-left-wrap > div.linear-swap-board.content-box > div.trade-content-box-title.linear-swap-board-title.swap-board-blue-title > div.linear-swap-board-open-close > h2:nth-child(2)";
        WebElement eOpen = driver.findElement(By.cssSelector(cssOpen));
        action.click(eOpen).build().perform();
        Thread.sleep(1000);
        // 闪电平仓
        log.info("[sell duo] choose flash gear...");
        String cssFlash = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[1]/div[1]/div[3]";
        WebElement eFlash = driver.findElement(By.xpath(cssFlash));
        action.click(eFlash).build().perform();
        Thread.sleep(1000);
        // 100%卖出
        log.info("[sell duo] choose hundred gear...");
        String cssSellHundred = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[3]/div/div/i[6]";
        WebElement eSellHundred = driver.findElement(By.xpath(cssSellHundred));
        action.click(eSellHundred).build().perform();
        Thread.sleep(1000);
        // 提交
        log.info("[sell duo] sell submit..");
        String cssSubmit = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/button";
        WebElement eSubmit = driver.findElement(By.xpath(cssSubmit));
        action.click(eSubmit).build().perform();
        Thread.sleep(1000);
        log.info("[sell duo] sell finished..");
    }

    private static void buyKong(ChromeDriver driver, WebDriverWait driverWait, Actions action)
            throws InterruptedException {
        // 点击开仓
        log.info("[buy kong] open position...");
        String cssOpen = "#root > div:nth-child(1) > div.linear-swap-trade > div.linear-swap-outer-right-wrap > div.linear-swap-inner-left-wrap > div.linear-swap-board.content-box > div.trade-content-box-title.linear-swap-board-title.swap-board-blue-title > div.linear-swap-board-open-close > h2:nth-child(1)";
        WebElement eOpen = driver.findElement(By.cssSelector(cssOpen));
        action.click(eOpen).build().perform();
        Thread.sleep(1000);
        // 点击选择挡位
        log.info("[buy kong] choose gear...");
        String cssGear = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[1]/div[1]/div[2]/div/span[2]";
        WebElement eGear = driver.findElement(By.xpath(cssGear));
        action.click(eGear).moveToElement(eGear).build().perform();
        Thread.sleep(1000);
        // 最优五档
        log.info("[buy kong] choose gear five...");
        String cssGearFive = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[1]/div[1]/div[2]/ul/li[2]/span";
        WebElement eGearFive = driver.findElement(By.xpath(cssGearFive));
        action.click(eGearFive).build().perform();
        Thread.sleep(1000);
        // 50%买入
        log.info("[buy kong] buy fifty..");
        String cssByForty = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/div[3]/div/div/i[4]";
        WebElement eByForty = driver.findElement(By.xpath(cssByForty));
        action.click(eByForty).build().perform();
        Thread.sleep(1000);
        // 提交
        log.info("[buy kong] buy submit..");
        String cssSubmit = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[4]/button";
        WebElement eSubmit = driver.findElement(By.xpath(cssSubmit));
        action.click(eSubmit).build().perform();
        Thread.sleep(1000);
        log.info("[buy kong] buy finished..");
    }

    private static void sellKong(ChromeDriver driver, WebDriverWait driverWait, Actions action)
            throws InterruptedException {
        // 点击平仓
        log.info("[sell kong] liquidate position...");
        String cssOpen = "#root > div:nth-child(1) > div.linear-swap-trade > div.linear-swap-outer-right-wrap > div.linear-swap-inner-left-wrap > div.linear-swap-board.content-box > div.trade-content-box-title.linear-swap-board-title.swap-board-blue-title > div.linear-swap-board-open-close > h2:nth-child(2)";
        WebElement eOpen = driver.findElement(By.cssSelector(cssOpen));
        action.click(eOpen).build().perform();
        Thread.sleep(1000);
        // 闪电平仓
        log.info("[sell kong] choose flash gear...");
        String cssFlash = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[1]/div[1]/div[3]";
        WebElement eFlash = driver.findElement(By.xpath(cssFlash));
        action.click(eFlash).build().perform();
        Thread.sleep(1000);
        // 100%卖出
        log.info("[sell kong] choose hundred gear...");
        String cssSellHundred = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div[3]/div/div/i[6]";
        WebElement eSellHundred = driver.findElement(By.xpath(cssSellHundred));
        action.click(eSellHundred).build().perform();
        Thread.sleep(1000);
        // 提交
        log.info("[sell kong] sell submit..");
        String cssSubmit = "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/button";
        WebElement eSubmit = driver.findElement(By.xpath(cssSubmit));
        action.click(eSubmit).build().perform();
        Thread.sleep(1000);
        log.info("[sell kong] sell finished..");
    }

    public static void priceCharge() {
        //                if (i == 0) {
//                    lastPrice = price;
//                }
//                BigDecimal subtract = price.subtract(lastPrice);
//                BigDecimal percent = subtract.divide(price, 4, RoundingMode.UP).multiply(new BigDecimal("120"));
//                if (percent.compareTo(new BigDecimal("0.1")) <= 0 && percent.compareTo(new BigDecimal("-0.1")) >= 0
//                        && !"".equals(lastSig)) {
//                    sig = "wait";
//                } else if (percent.compareTo(BigDecimal.ZERO) >= 0) {
//                    sig = "up";
//                    if ("down".equals(lastSig)) {
//                        log.info("[buy duo] buy starting buy price {}...", price);
//                        buyDuo(driver, driverWait, action);
//                        sellKong(driver, driverWait, action);
//                    }
//                } else {
//                    sig = "down";
//                    if ("up".equals(lastSig)) {
//                        log.info("[sell duo] sell starting sell price {}...", price);
//                        sellDuo(driver, driverWait, action);
//                        buyKong(driver, driverWait, action);
//                    }
//                }
//                lastPrice = price;
//                if (!"wait".equals(sig)) {
//                    lastSig = sig;
//                }

    }

}
