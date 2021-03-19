package com.example.designpatterntemplate.group;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.example.designpatterntemplate.HttpClient;
import com.example.designpatterntemplate.NetMall;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DangDangNetMail extends NetMall {

    public DangDangNetMail(String uId, String uPwd) {
        super(uId, uPwd);
    }

    @Override
    protected Boolean login(String uId, String uPwd) {
        log.info("模拟当当用户登录 uId: {} uPwd: {}", uId, uPwd);
        return true;
    }

    @Override
    protected Map<String, String> reptile(String skuUrl) {
        String str = HttpClient.doGet(skuUrl);
        Pattern p9 = Pattern.compile("(?<=title\\>).*(?=</title)");
        Matcher m9 = p9.matcher(str);
        Map<String, String> map = new ConcurrentHashMap<>();
        if (m9.find()) {
            map.put("name", m9.group());
        }
        map.put("price", "4548.00");
        log.info("模拟当当商品爬⾍虫解析：{} | {} 元 {}", map.get("name"), map.get("price"), skuUrl);
        return null;
    }

    @Override
    protected String createBase64(Map<String, String> goodsInfo) {
        log.info("模拟生成京东商品base64海报");
        byte[] bytes = JSON.toJSONString(goodsInfo).getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

}
