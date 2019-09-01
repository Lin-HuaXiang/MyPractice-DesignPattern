package com.designpattern.chapter33.command03;

public class UnknowActor implements IActor {

    @Override
    public void playact(String context) {
        System.out.println("普通演员：" + context);
    }
}
