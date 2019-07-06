package com.designpattern.chapter12_proxy_pattern.command09;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class RealSubjectNine implements SubjectNine {

    /**
     * business logic processing
     */
    @Override
    public void doSomething(String cmd) {
        System.out.println("do something ---> " + cmd);
    }
}
