package com.example.designpatternmediator;

import java.io.Reader;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.example.designpatternmediator.mediator.Resources;
import com.example.designpatternmediator.mediator.SqlSession;
import com.example.designpatternmediator.mediator.SqlSessionFactory;
import com.example.designpatternmediator.mediator.SqlSessionFactoryBuilder;
import com.example.designpatternmediator.po.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternMediatorApplicationTests {

	@Test
	void contextLoads() {

	}

	@Test
	public void testQueryUserInfoById() {
		String resource = "mybatis-config-datasource.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session = sqlMapper.openSession();
			try {
				User user = session.selectOne("com.example.designpatternmediator.dao.IUserDao.queryUserInfoById", 1L);
				log.info("测试结果: {}", JSON.toJSONString(user));
			} catch (Exception e) {
				//TODO: handle exception
			} finally {
				session.close();
				reader.close();
			}

		} catch (Exception e) {
			//TODO: handle exception
		}
	}

}
