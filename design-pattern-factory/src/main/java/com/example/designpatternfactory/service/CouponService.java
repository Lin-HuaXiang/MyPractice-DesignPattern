package com.example.designpatternfactory.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CouponService {


    public CouponResult sendCoupon(String uId, String commodityId, String bizId) {
        log.info("uId:{}, commodityId:{}, bizId:{}", uId, commodityId, bizId);
        CouponResult couponResult = new CouponResult();
        couponResult.setCode("0000");
        couponResult.setInfo("");
        return couponResult;
    }
    
}
