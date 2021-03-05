package com.example.designpatternadaptor;

import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.example.designpatternadaptor.design.MQAdapter;
import com.example.designpatternadaptor.design.OrderAdapterService;
import com.example.designpatternadaptor.design.RebateInfo;
import com.example.designpatternadaptor.design.impl.InsideOrderService;
import com.example.designpatternadaptor.design.impl.POPOrderAdapterServiceImpl;
import com.example.designpatternadaptor.mq.CreateAccount;
import com.example.designpatternadaptor.mq.OrderMq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternAdaptorApplicationTests {

	@Test
	void contextLoads() throws Exception {
		CreateAccount createAccount = new CreateAccount();
		createAccount.setNumber("100001");
		createAccount.setAddress("河北北省.廊坊市.⼴广阳区.⼤大学⾥里里职业技术学院");
		createAccount.setAccountDate(new Date());
		createAccount.setDesc("在校开户");

		HashMap<String, String> link01 = new HashMap<>();
		link01.put("userId", "number");
		link01.put("bizId", "number");
		link01.put("bizTime", "accountDate");
		link01.put("desc", "desc");
		RebateInfo rebateInfo01 = MQAdapter.filter(createAccount.toString(), link01);
		System.out.println("mq.createAccount(适配前)" + createAccount.toString());
		System.out.println("mq.createAccount(适配后)" + JSON.toJSONString(rebateInfo01));

		System.out.println("");

		OrderMq orderMq = new OrderMq();
		orderMq.setUid("100001");
		orderMq.setSku("10928092093111123");
		orderMq.setOrderId("100000890193847111");
		orderMq.setCreateOrderTime(new Date());

		HashMap<String, String> link02 = new HashMap<>();
		link02.put("userId", "uid");
		link02.put("bizId", "orderId");
		link02.put("bizTime", "createOrderTime");
		RebateInfo rebateInfo02 = MQAdapter.filter(orderMq.toString(), link02);

		System.out.println("mq.orderMq(适配前)" + orderMq.toString());
		System.out.println("mq.orderMq(适配后)" + JSON.toJSONString(rebateInfo02));
	}


	@Test
	public void testItfAdapter() {
		OrderAdapterService popOrderAdapterService = new POPOrderAdapterServiceImpl();
		System.out.println("判断首单，接口适配（POP）：" + popOrderAdapterService.isFirst("100001"));

		OrderAdapterService insideOrderService = new InsideOrderService();
		System.out.println("判断首单、接口适配（自营）：" + insideOrderService.isFirst("100001"));
	}

}
