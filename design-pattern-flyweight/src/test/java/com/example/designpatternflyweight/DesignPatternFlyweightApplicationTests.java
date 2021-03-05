package com.example.designpatternflyweight;

import com.alibaba.fastjson.JSON;
import com.example.designpatternflyweight.controller.ActivityController;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternFlyweightApplicationTests {

	@Test
	void contextLoads() {
	}

	private ActivityController activityController = new ActivityController();

	@Test
	public void testQueryActivityInfo() throws Exception {
		for (int idx = 0; idx < 10; idx++) {
			Long req = 10001L;
			Activity activity = activityController.queryActivityInfo(req);
			log.info("测试结果：{} {}", req, JSON.toJSONString(activity));
			Thread.sleep(1200);
		}
	}

}
