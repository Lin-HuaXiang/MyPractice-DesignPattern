package com.designpattern.chapter12_proxy_pattern.command09;

public class MainClientNineTwo {


    public static void main(String[] args) {
        // define a subject
        SubjectNine subject = new RealSubjectNine();
        // define subject proxy, handler are handled by the dynamic proxy class content
        SubjectNine proxy = SubjectDynamicProxyNine.newProxyInstance(subject);
        // proxy behavior
        String cmd = "finish";
        proxy.doSomething(cmd);
    }
}
