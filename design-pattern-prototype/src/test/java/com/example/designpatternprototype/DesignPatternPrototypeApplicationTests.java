package com.example.designpatternprototype;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternPrototypeApplicationTests {

	@Test
	void contextLoads() throws CloneNotSupportedException {
		QuestionBankControllerNew questionBankController = new QuestionBankControllerNew();
		System.out.println(questionBankController.createPager("花花", "1000001921032"));
		System.out.println(questionBankController.createPager("⾖豆⾖豆", "1000001921051"));
		System.out.println(questionBankController.createPager("⼤大宝", "1000001921987"));
	}

}
