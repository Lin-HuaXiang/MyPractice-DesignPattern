package com.example.designpatternsingleton;

import com.example.designpatternsingleton.singleton.Singleton07;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternSingletonApplicationTests {

	@Test
	void contextLoads() {
		Singleton07.INSTANCE.test();
	}

}
 