package com.example.designpatternstrategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.designpatternstrategy.event.MJCouponDiscount;
import com.example.designpatternstrategy.event.NYGCouponDiscount;
import com.example.designpatternstrategy.event.ZJCouponDiscount;
import com.example.designpatternstrategy.event.ZKCouponDiscount;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class DesignPatternStrategyApplicationTests {

	@Test
	void contextLoads() {
		Context<Double> context = new Context<>(new ZJCouponDiscount());
		BigDecimal discountAmount = context.discountAmount(10D, BigDecimal.valueOf(100));
		log.info("测试结果：只减优惠金额 {}", discountAmount);
	}

	@Test
	public void testmj() {
		Context<Map<String, String>> context = new Context<Map<String, String>>(new MJCouponDiscount());
		Map<String, String> mapReq = new HashMap<>();
		mapReq.put("x", "100");
		mapReq.put("n", "10");
		BigDecimal discountAmount = context.discountAmount(mapReq, new BigDecimal(100));
		log.info("测试结果：满减优惠后金额 {}", discountAmount);
	}

	@Test
	public void testZk() {
		Context<Double> context = new Context<Double>(new ZKCouponDiscount());
		BigDecimal discountAmount = context.discountAmount(0.9D, new BigDecimal(100));
		log.info("测试结果：折扣金额9折后金额 {}", discountAmount);
	}


	@Test
	public void testNyg() {
		Context<Double> context = new Context<Double>(new NYGCouponDiscount());
		BigDecimal discountAmount = context.discountAmount(90D, new BigDecimal(100));
		log.info("测试结果：n元购优惠后金额 {}", discountAmount);
	}


}
