package com.designpattern.chapter12_proxy_pattern.command09;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class SubjectDynamicProxyNine extends DynamicProxyNine {

    public static <T> T newProxyInstance(SubjectNine subject) {
        // acquire class loader
        ClassLoader classLoader = subject.getClass().getClassLoader();
        // acquire interfaces array
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        // acquire handler
        MyInvocationHandlerNine myInvocationHandler = new MyInvocationHandlerNine(subject);
        return newProxyInstance(classLoader, interfaces, myInvocationHandler);
    }
}
