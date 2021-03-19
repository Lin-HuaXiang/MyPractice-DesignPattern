package com.example.designpatterntemplate;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class NetMall {

    String uId;
    String uPwd;

    public NetMall(String uId, String uPwd) {
        this.uId = uId;
        this.uPwd = uPwd;
    }

    public String generateGoodsPoster(String skuUrl) {
        if (Boolean.FALSE.equals(login(uId, uPwd))) return null;
        Map<String, String> reptile = reptile(skuUrl);
        return createBase64(reptile);
    }

    protected abstract Boolean login(String uId, String uPwd);

    protected abstract Map<String, String> reptile(String skuUrl);

    protected abstract String createBase64(Map<String, String> goodsInfo);
    
}
