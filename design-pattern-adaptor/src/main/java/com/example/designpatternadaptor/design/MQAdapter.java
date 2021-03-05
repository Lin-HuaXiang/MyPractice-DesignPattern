package com.example.designpatternadaptor.design;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class MQAdapter {
    
    public static RebateInfo filter(String strJson, Map<String, String> link) throws Exception {
        return filter(JSON.parseObject(strJson, Map.class), link);
    }

    public static RebateInfo filter(Map obj, Map<String, String> link) throws Exception {
        RebateInfo rebateInfo = new RebateInfo();
        for (String key : link.keySet()) {
            Object val = obj.get(link.get(key));
            RebateInfo.class.getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), String.class).invoke(rebateInfo, val.toString());
        }
        return rebateInfo;
    }
}
