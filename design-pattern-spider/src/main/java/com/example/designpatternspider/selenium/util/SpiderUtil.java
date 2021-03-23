package com.example.designpatternspider.selenium.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class SpiderUtil {
    private static final Logger log = LoggerFactory.getLogger(SpiderUtil.class);
    private static int maxWait = 60;

    public static void requestData(String url, WebDriver driver) throws Exception {
        long t1 = System.currentTimeMillis();
        try {
            log.info("visit url：{}", url);
            driver.get(url);
        } catch (TimeoutException e) {
            // 当页面加载时间超过设定时间，通过执行Javascript来stop加载，即可执行后续动作
            ((JavascriptExecutor) driver).executeScript("window.stop()");
            try {
                log.info("visit again url：{}", url);
                driver.get(url);
            } catch (TimeoutException ex) {
                // 当页面加载时间超过设定时间，通过执行Javascript来stop加载，即可执行后续动作
                ((JavascriptExecutor) driver).executeScript("window.stop()");
            }
        }
        log.info("visit time: {}", (System.currentTimeMillis() - t1));
        Thread.sleep(100);

        for (int times = 0; times < 3; times++) {
            if (isElementPresent(By.id("reload-button"), driver)) {
                driver.findElement(By.id("reload-button")).click();
                break;
            }
        }
    }

    public static void requestData(String url, WebDriver driver, long waitTime) throws Exception {
        long t1 = System.currentTimeMillis();
        try {
            log.info("访问url：{}", url);
            driver.get(url);
        } catch (TimeoutException e) {
            // 当页面加载时间超过设定时间，通过执行Javascript来stop加载，即可执行后续动作
            ((JavascriptExecutor) driver).executeScript("window.stop()");
        }
        log.info("访问时间:{}", (System.currentTimeMillis() - t1));
        Thread.sleep(waitTime);
        for (int times = 0; times < 3; times++) {
            if (isElementPresent(By.id("reload-button"), driver)) {
                driver.findElement(By.id("reload-button")).click();
                break;
            }
        }
    }

    public static void requestDataAliexpress(String url, WebDriver driver) throws Exception {
        long t1 = System.currentTimeMillis();
        try {
            log.info("访问url：{}", url);
            driver.get(url);
        } catch (TimeoutException e) {
            // 当页面加载时间超过设定时间，通过执行Javascript来stop加载，即可执行后续动作
            ((JavascriptExecutor) driver).executeScript("window.stop()");
            throw e;
        }
        log.info("访问时间:{}", (System.currentTimeMillis() - t1));
        Thread.sleep(100);
        for (int times = 0; times < 3; times++) {
            if (isElementPresent(By.id("reload-button"), driver)) {
                driver.findElement(By.id("reload-button")).click();
                break;
            }
        }
    }


    public static boolean isElementPresent(By by, WebDriver driver) {
        return isElementPresent(by, driver, maxWait, 0);
    }

    public static boolean isAmazonElementPresent(By by, WebDriver driver) {
        return isElementPresent(by, driver, maxWait, 1);
    }

    /**
     * 查找元素
     *
     * @param by               元素位置
     * @param driver           驱动
     * @param afterWaitSeconds 全局配置隐形等待时间
     * @param waitSeconds      当前元素隐形等待时间
     * @return
     */
    public static boolean isElementPresent(By by, WebDriver driver, int afterWaitSeconds, int waitSeconds) {
        try {
            driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
            driver.findElement(by);
            return true;
        } catch (Exception e) {
        } finally {
            driver.manage().timeouts().implicitlyWait(afterWaitSeconds, TimeUnit.SECONDS);
        }
        return false;
    }

    public static WebElement getElement(By by, WebElement webElement, WebDriver driver) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return webElement.findElement(by);
        } catch (Exception e) {
        } finally {
            driver.manage().timeouts().implicitlyWait(maxWait, TimeUnit.SECONDS);
        }
        return null;
    }

    public static List<WebElement> getElements(By by, WebElement webElement, WebDriver driver) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            List<WebElement> elements = webElement.findElements(by);
            return elements;
        } catch (Exception e) {
        } finally {
            driver.manage().timeouts().implicitlyWait(maxWait, TimeUnit.SECONDS);
        }
        return null;
    }

    public static List<WebElement> getElements(By by, WebDriver driver) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            List<WebElement> elements = driver.findElements(by);
            return elements;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.manage().timeouts().implicitlyWait(maxWait, TimeUnit.SECONDS);
        }
        return null;
    }

    public static WebElement getElement(By by, WebDriver driver) {
        return getElement(by, driver, maxWait, 0);
    }

    public static void exeJs(String js, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(js);
    }

    public static WebElement getElement(By by, WebDriver driver, int afterWaitSeconds, int waitSeconds) {
        try {
            driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
            return driver.findElement(by);
        } catch (Exception e) {
        } finally {
            driver.manage().timeouts().implicitlyWait(afterWaitSeconds, TimeUnit.SECONDS);
        }
        return null;
    }

    public static void selectOptions(WebElement element, String optionsText, WebDriver driver) {
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            new Select(element).selectByVisibleText(optionsText);
        } catch (Exception e) {
            log.info("选择下拉框失败");
        } finally {
            driver.manage().timeouts().implicitlyWait(maxWait, TimeUnit.SECONDS);
        }
    }

    /**
     * 截图,全屏
     *
     * @param driver
     * @param filePath
     */
    public static boolean snapShot(WebDriver driver, String filePath) {
        TakesScreenshot drivername = (TakesScreenshot) driver;
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
            return true;
        } catch (IOException e) {
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        } finally {
            System.out.println("screen shot finished");
        }
        return false;
    }

    /**
     * 关闭浏览器
     *
     * @param driver
     */
    public static void closeBrowser(WebDriver driver) {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            ChromeDriverUtil.closeChromeDriverTimer();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static String getHtml(WebElement element) {
        String innerHTML = element.getAttribute("innerHTML");
        return innerHTML;
    }

    public static String getHtml(By by, WebDriver driver) {
        WebElement element = getElement(by, driver);
        if (element == null) return null;
        String innerHTML = element.getAttribute("innerHTML");
        return innerHTML;
    }

    public static void elementClick(By by, WebDriver driver) {
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        webElement.click();
    }

    public static void elementJsClick(By by, WebDriver driver) {
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    public static void elementJsClick(WebElement webElement, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    public static void elementJsClick(WebElement webElement, WebDriver driver, long time) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
        if (!StringUtils.isEmpty(time)) {
            Thread.sleep(time);
        }
    }

    public static void windowOpen(String url, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "')");
    }

    /**
     * robot点击
     *
     * @param x
     * @param y
     */
    // public static void robotClick(int x, int y) {
    //     try {
    //         Robot bot = new Robot();
    //         bot.setAutoDelay(500);
    //         bot.mouseMove(x, y);
    //         bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    //         bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    //         bot.delay(200);
    //     } catch (Exception e) {
    //     }
    // }

    public static List<WebElement> getElementsUntil(By by, WebDriver driver, long second) {
        List<WebElement> list = getUntil(() -> getElements(by, driver), second, List.class);
        return list;
    }

    public static WebElement getElementUntil(By by, WebDriver driver, long second) {
        WebElement element = getUntil(() -> getElement(by, driver), second, WebElement.class);
        return element;
    }

    public static Object getUntil(Until.GetUntilAction action, long second) {
        return getUntil(action, second, Object.class);
    }

    public static <T> T getUntil(Until.GetUntilAction<T> action, long second, Class<T> cla) {
        long t1 = System.currentTimeMillis();
        T result = null;
        while (true) {
            T t = null;
            try {
                t = action.exec();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (t != null || System.currentTimeMillis() - t1 >= second * 1000) {
                result = t;
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String removeOtherWindowHandle(WebDriver driver) {
        String thisWindowHandle = driver.getWindowHandle();
        boolean hasOther = false;
        for (String windowHandle : driver.getWindowHandles()) {
            if (!thisWindowHandle.equals(windowHandle)) {
                hasOther = true;
                driver.switchTo().window(windowHandle).close();
            }
        }
        if (hasOther) driver.switchTo().window(thisWindowHandle);
        return thisWindowHandle;
    }


    /*public static void downloadedFile(String filePath, long timeout) throws Exception {
        downloadedFile(filePath,timeout,".crdownload");
    }*/

    /*public static void downloadedFile(String filePath, long timeout,String suffix) throws Exception {
        File tmpFile = new File(filePath);
        File[] files = tmpFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            File tmp = files[i];
            long length = -1;
            long t1 = System.currentTimeMillis();
            if (tmp.exists() && tmp.isFile()) {
                if (tmp.toString().endsWith(suffix)) {
                    while (FileUtil.isFileWriting(tmp.getAbsolutePath()) || tmp.length() != length) {
                        if (System.currentTimeMillis() - t1 > timeout) break;
                        length = tmp.length();
                        Thread.sleep(10 * 1000);
                        log.info("file is downloading");
                    }
                }
            }
        }
    }*/

    /*public static boolean boolDownloadedFile(String filePath, long timeout) throws Exception {
        File tmpFile = new File(filePath);
        File[] files = tmpFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            File tmp = files[i];
            long length = -1;
            long t1 = System.currentTimeMillis();
            if (tmp.exists() && tmp.isFile()) {
                if (tmp.toString().endsWith(".crdownload")) {
                    while (FileUtil.isFileWriting(tmp.getAbsolutePath()) || tmp.length() != length) {
                        if (System.currentTimeMillis() - t1 > timeout) break;
                        length = tmp.length();
                        Thread.sleep(1 * 1000);
                        log.info("file is downloading");
                    }
                }
                if (tmp.toString().endsWith(".crdownload")) {
                    return false;
                }
            }
        }
        return true;
    }*/

 /*   public static boolean downloadedFileV1(String filePath) throws Exception {
        Thread.sleep(1000);
        boolean result = true;
        File tmpFile = new File(filePath);
        File[] files = tmpFile.listFiles();
        if (files.length < 1) {
            result = false;
            return result;
        }
        File file = files[0];
        while (true) {
            Thread.sleep(2 * 1000);
            System.out.println("sleep");
            if (!FileUtil.isFileWriting(file.getAbsolutePath())) {
                Thread.sleep(2 * 1000);
                File fileDown = new File(filePath);
                File fileFinish = fileDown.listFiles()[0];
                if (fileFinish.getName().endsWith(".crdownload")) {
                    fileDown.delete();
                    result = false;
                }
                break;
            }
        }
        return result;
    }*/

    /*public static boolean downloadedFileV2(String filePath) throws Exception {
        Thread.sleep(3000);
        boolean result = true;
        File tmpFile = new File(filePath);
        File[] files = tmpFile.listFiles();
        if (files.length < 1) {
            result = false;
            return result;
        }
        File file = files[0];
        while (true) {
            Thread.sleep(2 * 1000);
            System.out.println("sleep");
            if (!FileUtil.isFileWriting(file.getAbsolutePath())) {
                Thread.sleep(2 * 1000);
                File fileDown = new File(filePath);
                File fileFinish = fileDown.listFiles()[0];
                if (!fileFinish.getName().endsWith(".pdf")) {
                    fileDown.delete();
                    result = false;
                }
                break;
            }
        }
        return result;
    }*/

    /**
     * file is downloading
     * 获取谷歌浏览器Cookie
     *
     * @param driver
     * @return
     */
    public static String getChromeCookie(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        String cookieStr = "";
        for (Cookie cookie : cookies) {
            cookieStr += cookie.getName() + "=" + cookie.getValue() + "; ";
        }
        return cookieStr;
    }

    public static void deleteChromDeleteCookie(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }
}
