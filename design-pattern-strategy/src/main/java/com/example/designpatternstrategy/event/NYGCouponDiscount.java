package com.example.designpatternstrategy.event;

import java.math.BigDecimal;

import com.example.designpatternstrategy.ICouponDiscount;

public class NYGCouponDiscount implements ICouponDiscount<Double> {

    /**
     * n元购买
     * 1. 无论原价多少钱都固定金额购买
     */

    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        return BigDecimal.valueOf(couponInfo);
    }




    
}
