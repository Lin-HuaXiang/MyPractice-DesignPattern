package com.example.designpatternfactory;

import com.example.designpatternfactory.service.GoodsService;
import com.example.designpatternfactory.store.ICommodity;
import com.example.designpatternfactory.store.impl.CardCommodityService;
import com.example.designpatternfactory.store.impl.CouponCommodityService;
import com.example.designpatternfactory.store.impl.GoodsCommodityService;

public class StoreFactory {

    public ICommodity getCommodityService(Integer commodityType) {

        if (null == commodityType)
            return null;
        if (1 == commodityType)
            return new CouponCommodityService();
        if (2 == commodityType)
            return new GoodsCommodityService();
        if (3 == commodityType)
            return new CardCommodityService();
        throw new RuntimeException("不存在的商品服务类型");

    }

}
