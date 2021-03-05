package com.example.designpatternfactory.store.impl;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.designpatternfactory.service.DeliverReq;
import com.example.designpatternfactory.service.GoodsService;
import com.example.designpatternfactory.store.ICommodity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoodsCommodityService implements ICommodity {


    private GoodsService goodsService = new GoodsService();

    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) {
        DeliverReq deliverReq = new DeliverReq();
        deliverReq.setUserName(queryUserName(uId));
        deliverReq.setUserPhone(queryUserName(uId));
        deliverReq.setSku(commodityId);
        deliverReq.setOrderId(bizId);
        deliverReq.setConsigneeUserName(extMap.get("consigneeUserName"));
        deliverReq.setConsigneeUserPhone(extMap.get("consigneeUserPhone"));
        deliverReq.setConsigneeUserName(extMap.get("consigneeUserAddress"));

        Boolean isSuccess = goodsService.deliverGoods(deliverReq);

        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[优惠卷]：{}", isSuccess);

        if (!isSuccess) {
            throw new RuntimeException("实物商品发送失败");
        }
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }
    
}
