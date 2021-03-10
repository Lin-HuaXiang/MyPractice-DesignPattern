package com.example.designpatternproxy;

import org.apache.naming.factory.BeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternProxyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testIUserDao() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
		IUserDao userDao = classPathXmlApplicationContext.getBean("userDao", IUserDao.class);
		String queryUserInfo = userDao.queryUserInfo("100001");
		log.info("测试结果{}", queryUserInfo);

	}

}
