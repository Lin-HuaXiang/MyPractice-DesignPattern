package com.example.designpatternbuilder;

import com.example.designpatternbuilder.design.Builder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternBuilderApplicationTests {

	@Test
	void contextLoads() {

		Builder builder = new Builder();

		System.out.println(builder.levelOne(132.52D).getDetail());
		System.out.println(builder.levelTwo(98.25D).getDetail());
		System.out.println(builder.levelThree(85.43D).getDetail());

	}

}
