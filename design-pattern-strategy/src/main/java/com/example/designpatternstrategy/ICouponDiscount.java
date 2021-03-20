package com.example.designpatternstrategy;

import java.math.BigDecimal;

public interface ICouponDiscount<T> {

    BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice);
    
}
