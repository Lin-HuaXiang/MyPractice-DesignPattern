package com.example.designpatterndecorator;

import com.example.designpatterndecorator.design.SsoInterceptor;
import com.example.designpatterndecorator.normal.LoginSsoDecorator;
import com.example.optimize.SsoDecorator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternDecoratorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testLoginSsoDecorator() {
		LoginSsoDecorator ssoDecorator = new LoginSsoDecorator();
		String request = "lsuccesshuahua";
		boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
		System.out.println("登录校验：" + request + (success ? "放行" : "拦截"));
	}

	@Test
	public void testLoginSsoDecoratorNew() {
		SsoInterceptor ssoInterceptor = new SsoInterceptor();
		com.example.optimize.LoginSsoDecorator ssoDecorator = new com.example.optimize.LoginSsoDecorator(ssoInterceptor);
		String request = "lsuccesshuahua";
		boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
		System.out.println("登录校验：" + request + (success ? " 放⾏" : " 拦截"));
	}



}
