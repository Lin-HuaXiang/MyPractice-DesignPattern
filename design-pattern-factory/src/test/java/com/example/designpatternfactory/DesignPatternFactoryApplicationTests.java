package com.example.designpatternfactory;

import java.util.HashMap;
import java.util.Map;

import com.example.designpatternfactory.store.ICommodity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternFactoryApplicationTests {

	@Test
	void contextLoads() throws Exception {
		StoreFactory storeFactory = new StoreFactory();
		// 1. 优惠券
		ICommodity commodityService = storeFactory.getCommodityService(1);
		commodityService.sendCommodity("10001", "EGM1023938910232121323432", "791098764902132", null);
		// 2. 实物商品
		ICommodity commodityService2 = storeFactory.getCommodityService(2);
		Map<String, String> extMap = new HashMap<>();
		extMap.put("consigneeUserName", "谢飞机");
		extMap.put("consigneeUserPhone", "13200292123");
		extMap.put("consigneeUserAddress", "吉林省.长春市.双阳区.xx街道.檀溪苑⼩小区.#18-2109");
		commodityService2.sendCommodity("10001", "9820198721311", "10230000201122113", extMap);
		// 3.第三方兑换卡
		ICommodity commodityService3 = storeFactory.getCommodityService(3);
		commodityService3.sendCommodity("10001", "AQY1xjkUodl8LO975GdfrYUio", null, null);
	}

}
