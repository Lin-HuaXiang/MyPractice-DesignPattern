package com.designpattern.chapter12_proxy_pattern.command09;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 * @param <T> generics
 */
public class DynamicProxyNine<T> {
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler) {
        // search join point, AOP framework uses metadata definitions
        if (true) {
            // execute @before before advice
            (new BeforeAdviceNine()).exec();
        }
        // execute the target and return to the result
        return (T) Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
