package com.example.designpatternmemo;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.example.designpatternmemo.design.Admin;
import com.example.designpatternmemo.design.ConfigFile;
import com.example.designpatternmemo.design.ConfigOriginator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternMemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test() {
		Admin admin = new Admin();
		ConfigOriginator configOriginator = new ConfigOriginator();

		configOriginator.setConfigFile(new ConfigFile("100001", "配置内容A=哈哈", new Date(), "小哥"));
		admin.append(configOriginator.saveMemento());

		configOriginator.setConfigFile(new ConfigFile("100002", "配置内容A=嘻嘻", new Date(), "小哥"));
		admin.append(configOriginator.saveMemento());

		configOriginator.setConfigFile(new ConfigFile("100003", "配置内容A=么么", new Date(), "小哥"));
		admin.append(configOriginator.saveMemento());

		configOriginator.setConfigFile(new ConfigFile("100004", "配置内容A=嘿嘿", new Date(), "小哥"));
		admin.append(configOriginator.saveMemento());

		// 历史配置
		configOriginator.getMemento(admin.undo());
		log.info("历史配置（回滚） undo: {}", JSON.toJSONString(configOriginator.getConfigFile()));

		// 历史配置
		configOriginator.getMemento(admin.undo());
		log.info("历史配置（回滚） undo: {}", JSON.toJSONString(configOriginator.getConfigFile()));

		// 历史配置
		configOriginator.getMemento(admin.redo());
		log.info("历史配置（前进） redo: {}", JSON.toJSONString(configOriginator.getConfigFile()));

		// 历史配置(获取)
		configOriginator.getMemento(admin.get("100002"));
		log.info("历史配置（获取） get：{}", JSON.toJSONString(configOriginator.getConfigFile()));
	}
}
