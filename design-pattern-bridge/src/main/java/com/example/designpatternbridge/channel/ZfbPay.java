package com.example.designpatternbridge.channel;

import java.math.BigDecimal;

import com.example.designpatternbridge.mode.IPayMode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZfbPay extends Pay {
    
    public ZfbPay(IPayMode payMode) {
        super(payMode);
    }

    public String transfer(String uId, String tradeId, BigDecimal amount) {
        log.info("模拟⽀支付宝渠道⽀支付划账开始。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
        boolean security = payMode.security(uId);
        log.info("模拟⽀支付宝渠道⽀支付⻛风控校验。uId：{} tradeId：{} security：{}", uId, tradeId, security);
        if (!security) {
            log.info("模拟⽀支付宝渠道⽀支付划账拦截。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
            return "0001";
        }
        log.info("模拟⽀支付宝渠道⽀支付划账成功。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
        return "0000";
    }
}
