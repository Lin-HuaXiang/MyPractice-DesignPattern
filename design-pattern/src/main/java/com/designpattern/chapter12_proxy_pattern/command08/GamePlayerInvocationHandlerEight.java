package com.designpattern.chapter12_proxy_pattern.command08;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */

public class GamePlayerInvocationHandlerEight implements InvocationHandler {

    /**
     * to represented
     */
    Class cls = null;

    /**
     * the instance being represented
     */
    Object obj = null;

    /**
     * who do I represent
     * @param obj
     */
    public GamePlayerInvocationHandlerEight(Object obj) {
        this.obj = obj;
    }

    /**
     * calls the proxies method
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.obj, args);
        // if it is client, send message, determine whether it is a login method
        String determineMethod = "login";
        if (determineMethod.equalsIgnoreCase(method.getName())) {
            System.out.println("someone is logging in with my account");
        }
        return null;
    }
}
