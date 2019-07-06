package com.designpattern.chapter12_proxy_pattern.command09;

import com.designpattern.chapter12_proxy_pattern.command03.Subject;

public class MainClientNine {


    public static void main(String[] args) {
        // define a subject
        SubjectNine subject = new RealSubjectNine();
        // define a handler
        MyInvocationHandlerNine handler = new MyInvocationHandlerNine(subject);
        SubjectNine proxy = DynamicProxyNine
                .newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), handler);
        // proxy behavior
        String cmd = "finish";
        proxy.doSomething(cmd);
    }
}
