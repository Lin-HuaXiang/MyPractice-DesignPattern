package com.example.designpatternchain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.designpatternchain.main.AuthLink;
import com.example.designpatternchain.main.AuthService;
import com.example.designpatternchain.main.Level1AuthLink;
import com.example.designpatternchain.main.Level2AuthLink;
import com.example.designpatternchain.main.Level3AuthLink;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternChainApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void before() {

	}

	@AfterEach
	public void after() {

	}

	@Test
	public void testAuthLink() throws ParseException {
		AuthLink authLink = new Level3AuthLink("1000013", "王工")
				.appendNext(new Level2AuthLink("1000012", "张经理")
				.appendNext(new Level1AuthLink("1000011", "段总")));
		;

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = f.parse("2020-6-18 12:00:00");
		log.info("测试结果：{}", "未加入负责人");
		log.info("测试结果：{}", authLink.doAuth("小傅哥", "1000998004813441", date));

		// 模拟三级负责人审批
		AuthService.auth("1000013", "1000998004813441");
		log.info("测试结果：{}", "加入三级负责人审批，王工");
		log.info("测试结果：{}", authLink.doAuth("小傅哥", "1000998004813441", date));

		// 模拟二级负责人审批
		AuthService.auth("1000012", "1000998004813441");
		log.info("测试结果：{}", "加入二级负责人审批，张经理");
		log.info("测试结果：{}", authLink.doAuth("小傅哥", "1000998004813441", date));

		// 模拟一级负责人审批
		AuthService.auth("1000011", "1000998004813441");
		log.info("测试结果：{}", "加入一级负责人审批，段总");
		log.info("测试结果：{}", authLink.doAuth("小傅哥", "1000998004813441", date));

	}

}
