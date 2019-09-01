package com.designpattern.chapter12_proxy_pattern.command09;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class MyInvocationHandlerNine implements InvocationHandler {

    /**
     * to represented
     */
    private Object target = null;

    /**
     * pass an object through the constructor
     * @param target
     */
    public MyInvocationHandlerNine(Object target) {
        this.target = target;
    }

    /**
     * proxy method
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // calls the proxies method
        return method.invoke(this.target, args);
    }
}
