package com.designpattern.chapter16_chain_of_responsibility_pattern.command01;

public class Husband implements IHandler {


    @Override
    public void handleMessage(IWomen women) {
        System.out.println("妻子的请示是：" + women.getRequest());
        System.out.println("丈夫的答复是：同意");
    }
}
