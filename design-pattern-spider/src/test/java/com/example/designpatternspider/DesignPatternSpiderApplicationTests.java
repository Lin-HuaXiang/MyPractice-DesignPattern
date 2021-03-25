package com.example.designpatternspider;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import com.example.designpatternspider.selenium.huobi.api.HuobiLinearSwapSpider;
import com.example.designpatternspider.selenium.huobi.api.HuobiOpenSpider;
import com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator.IndicatorLink;
import com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator.MacdIndicatorLink;
import com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator.MacdWebsiteIndicatorLink;
import com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator.RsiIndicatorLink;
import com.example.designpatternspider.selenium.huobi.observer.event.SwapService;
import com.example.designpatternspider.selenium.huobi.strategy.futures.usdt.fil.WebsiteFilSwapTrade;
import com.example.designpatternspider.selenium.huobi.strategy.trade.trade.ISwapTrade;
import com.example.designpatternspider.selenium.util.SpiderUtil;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternSpiderApplicationTests {

	private WebDriver driver;
	private Actions action;
	private WebDriverWait driverWait;

	@BeforeEach
	public void before() {
		driver = HuobiOpenSpider.driverBuilderChromeHeadLess();
		action = new Actions(driver);
		driverWait = new WebDriverWait(driver, 30);
	}

	@AfterEach
	public void after() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	void testContextLoads() throws Exception {

		ISwapTrade swapTrade = new WebsiteFilSwapTrade();
		SwapService swapService = new SwapService(swapTrade);
		// Gets the Handle for the current window
		String parentHandle = driver.getWindowHandle();
		// Find the button to open a new window
		log.info("Open a new two window");
		// 点击新窗口
		((JavascriptExecutor) driver).executeScript("window.open()");
		((JavascriptExecutor) driver).executeScript("window.open()");
	
		// 获取所有的Handle
		Set<String> handles = driver.getWindowHandles();
		// 判断是否打开了新窗口
		Iterator<String> iterator = handles.iterator();
		String main = iterator.next();
		String kline = iterator.next();
		String api = iterator.next();

		// open kline
		driver.switchTo().window(kline);
		SpiderUtil.requestData(HuobiLinearSwapSpider.KLINE_URL, driver);
		// open api
		driver.switchTo().window(api);
		SpiderUtil.requestData(HuobiOpenSpider.API_URL, driver);

		log.info("main window Handle is {}", main);
		log.info("kline window Handle is {}", main);
		log.info("api window Handle is {}", main);

		IndicatorLink macdIndicator = new MacdWebsiteIndicatorLink(kline);
		IndicatorLink rsiIndicator = new RsiIndicatorLink(api);

		int n = 1;
		for (int i = 0; i < n; i++) {

			try {
				rsiIndicator.work(driver, driverWait, action);
				swapService.notify(rsiIndicator, driver, driverWait, action);

			} catch (Exception e) {
				log.error("第{}次获取数据，失败了", i + 1, e);
			}
			Calendar c1 = Calendar.getInstance(); // 当前日期
			int sec = c1.get(Calendar.SECOND);
			int intervalSec = 60 - sec;
			Thread.sleep(intervalSec * 1000);
		}

		assertTrue(true);

	}

}
