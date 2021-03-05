package com.example.designpatternabstractfactory.factory.impl;

import java.util.concurrent.TimeUnit;

import com.example.designpatternabstractfactory.factory.ICacheAdapter;
import com.example.designpatternabstractfactory.matter.IIR;

public class IIRCacheAdapter implements ICacheAdapter {

    private IIR iir = new IIR();

    public String get(String key) {
        return iir.get(key);
    }

    public void set(String key, String value) {
        iir.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        iir.setExpire(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        iir.del(key);
    }
    

}
