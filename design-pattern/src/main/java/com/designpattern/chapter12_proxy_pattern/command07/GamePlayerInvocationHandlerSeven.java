package com.designpattern.chapter12_proxy_pattern.command07;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class GamePlayerInvocationHandlerSeven implements InvocationHandler {

    /**
     * to represented
     */
    Class cls = null;

    /**
     * the instance being represented
     */
    Object obj = null;

    /**
     * Who do I represent
     * @param obj
     */
    public GamePlayerInvocationHandlerSeven(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.obj, args);
        return result;
    }
}
