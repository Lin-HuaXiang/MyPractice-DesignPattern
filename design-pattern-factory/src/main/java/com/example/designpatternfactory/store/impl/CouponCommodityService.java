package com.example.designpatternfactory.store.impl;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.designpatternfactory.service.CouponResult;
import com.example.designpatternfactory.service.CouponService;
import com.example.designpatternfactory.store.ICommodity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CouponCommodityService implements ICommodity {

    private Logger log = LoggerFactory.getLogger(CouponCommodityService.class);

    private CouponService couponService = new CouponService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap)
            throws Exception {
        CouponResult couponResult = couponService.sendCoupon(uId, commodityId, bizId);
        log.info("请求参数[优惠卷 => uid:{} commodityId:{} bizId:{} extMap:{} ", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[优惠券]：{}", JSON.toJSON(couponResult));
        if (!"0000".equals(couponResult.getCode())) {
            throw new RuntimeException(couponResult.getInfo());
        }
    }

    
    
}
