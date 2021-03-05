package com.example.designpatternfactory.store.impl;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.designpatternfactory.service.IQiYiCardService;
import com.example.designpatternfactory.store.ICommodity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardCommodityService implements ICommodity {

    private IQiYiCardService iQiYiCardService = new IQiYiCardService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap)
            throws Exception {
        String mobile = queryUserMobile(uId);
        iQiYiCardService.grantToken(mobile, bizId);
        log.info("请求参数[爱奇艺兑换卡] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[爱奇艺兑换卡]：success");
    }
    
    public String queryUserMobile(String uId) {
        return "15200101232";
    }
}
