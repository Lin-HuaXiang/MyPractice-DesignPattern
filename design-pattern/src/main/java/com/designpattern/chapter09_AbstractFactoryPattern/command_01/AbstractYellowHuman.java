package com.designpattern.chapter09_AbstractFactoryPattern.command_01;

public abstract class AbstractYellowHuman implements Human {

    public void getColor() {
        System.out.println("黄色人种的皮肤颜色都是黄色的");
    }

    public void talk() {
        System.out.println("黄色人种说话，一般说的都是双字节");
    }
}
