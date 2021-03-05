package com.example.designpatternabstractfactory;

import com.example.designpatternabstractfactory.factory.JDKProxy;
import com.example.designpatternabstractfactory.factory.impl.EGMCacheAdapter;
import com.example.designpatternabstractfactory.factory.impl.IIRCacheAdapter;
import com.example.designpatternabstractfactory.itf.CacheService;
import com.example.designpatternabstractfactory.itf.CacheServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternAbstractFactoryApplicationTests {

	@Test
	void contextLoads() throws Exception {

		CacheService proxyEGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
		proxyEGM.set("user_name_01", "小傅哥");
		String val01 = proxyEGM.get("user_name_01");
		System.out.println(val01);


		CacheService proxyIIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
		proxyIIR.set("user_name_01", "小傅哥");
		String val02 = proxyIIR.get("user_name_01");
		System.out.println(val02);
	}

}
