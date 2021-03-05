package com.example.designpatternbridge.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayController {

    private Logger logger = LoggerFactory.getLogger(PayController.class);

    public boolean doPay(String uId, String tradeId, BigDecimal amount, int channelType, int modeType) {
        // 微信⽀支付
        if (1 == channelType) {
            logger.info("模拟微信渠道⽀支付划账开始。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
            if (1 == modeType) {
                logger.info("密码⽀支付，⻛风控校验环境安全");
            } else if (2 == modeType) {
                logger.info("⼈人脸⽀支付，⻛风控校验脸部识别");
            } else if (3 == modeType) {
                logger.info("指纹⽀支付，⻛风控校验指纹信息");
            }
        }
        // ⽀支付宝⽀支付
        else if (2 == channelType) {
            logger.info("模拟⽀支付宝渠道⽀支付划账开始。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
            if (1 == modeType) {
                logger.info("密码⽀支付，⻛风控校验环境安全");
            } else if (2 == modeType) {
                logger.info("⼈人脸⽀支付，⻛风控校验脸部识别");
            } else if (3 == modeType) {
                logger.info("指纹⽀支付，⻛风控校验指纹信息");
            }
        }
        return true;
    }

}
