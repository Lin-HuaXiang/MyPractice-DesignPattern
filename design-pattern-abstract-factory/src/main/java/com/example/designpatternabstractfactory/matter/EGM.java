package com.example.designpatternabstractfactory.matter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EGM {
    
    private Map<String, String> dataMap = new ConcurrentHashMap<>();

    public String gain(String key) {
        log.info("BGM获取数据key:{}", key);
        return dataMap.get(key);
    }

    public void set(String key, String value) {
        log.info("BGM写入数据key:{}, value:{}", key, value);
        dataMap.put(key, value);
    }

    public void setEx(String key, String value, long timeout, TimeUnit timeUnit) {
        log.info("BGM写入数据key:{}, value:{}, timeout:{}, timeUnit:{}", key, value, timeout, timeUnit.toString());
        dataMap.put(key, value);
    }

    public void delete(String key) {
        log.info("BGM删除数据key:{}", key);
        dataMap.remove(key);
    }

}
