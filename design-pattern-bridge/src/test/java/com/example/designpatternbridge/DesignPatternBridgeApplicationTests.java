package com.example.designpatternbridge;

import java.math.BigDecimal;

import com.example.designpatternbridge.channel.Pay;
import com.example.designpatternbridge.channel.WxPay;
import com.example.designpatternbridge.channel.ZfbPay;
import com.example.designpatternbridge.controller.PayController;
import com.example.designpatternbridge.mode.PayFaceMode;
import com.example.designpatternbridge.mode.PayFingerprintMode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternBridgeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testPay() {
		PayController pay = new PayController();
		System.out.println("\r\n模拟测试场景：微信支付、人脸方式");
		pay.doPay("weixin_1092033111", "100000109893", new BigDecimal(100), 1, 2);
		System.out.println("\r\n模拟测试场景；⽀支付宝⽀支付、指纹⽅方式。");
		pay.doPay("jlu19dlxo111", "100000109894", new BigDecimal(100), 2, 3);
	}

	@Test
	public void testPayBridge() {
		System.out.println("\r\n模拟测试场景；微信⽀支付、⼈人脸⽅方式。");
		Pay wxPay = new WxPay(new PayFaceMode());
		wxPay.transfer("weixin_1092033111", "100000109893", new BigDecimal(100));
		System.out.println("\r\n模拟测试场景；⽀支付宝⽀支付、指纹⽅方式。");
		Pay zfbPay = new ZfbPay(new PayFingerprintMode());
		zfbPay.transfer("jlu19dlxo111", "100000109894", new BigDecimal(100));
	}

}
