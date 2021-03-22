package com.example.designpatternvisitor;

import com.example.designpatternvisitor.visitor.impl.Parent;
import com.example.designpatternvisitor.visitor.impl.Principal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jdk.internal.net.http.common.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternVisitorApplicationTests {

	@Test
	void contextLoads() {

		DataView dataView = new DataView();

		log.info("\r\n家长视角访问：");
		dataView.show(new Parent());

		log.info("\r\n校长视角访问");
		dataView.show(new Principal());
	}

}
