package com.example.designpatterntemplate;

import com.example.designpatterntemplate.group.JDNetMall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternTemplateApplicationTests {

	@Test
	void contextLoads() {
		NetMall netMall = new JDNetMall("1000001", "******");
		String base64 = netMall.generateGoodsPoster("https://item.jd.com/100008348542.html");
		log.info("测试结果: {}", base64);
	}

}
