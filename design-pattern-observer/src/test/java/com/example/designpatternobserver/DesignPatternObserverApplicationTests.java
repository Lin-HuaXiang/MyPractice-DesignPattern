package com.example.designpatternobserver;

import com.alibaba.fastjson.JSON;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternObserverApplicationTests {

	@Test
	void contextLoads() {
		LotteryService lotteryService = new LotteryServiceImpl();
		LotteryResult result = lotteryService.draw("2765789109876");
		log.info("测试结果{}", JSON.toJSONString(result));
	}

}
