package com.example.designpatternabstractfactory.factory.impl;

import java.util.concurrent.TimeUnit;

import com.example.designpatternabstractfactory.factory.ICacheAdapter;
import com.example.designpatternabstractfactory.matter.EGM;

public class EGMCacheAdapter implements ICacheAdapter {

    private EGM egm = new EGM();

    public String get(String key) {
        return egm.gain(key);
    }

    public void set(String key, String value) {
        egm.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        egm.setEx(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        egm.delete(key);
    }
    
}
