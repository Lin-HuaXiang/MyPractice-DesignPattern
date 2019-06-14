package com.designpattern.chapter05_LawofDemeter.command_4;

import java.util.Random;

public class Wizard {

    private Random random = new Random(System.currentTimeMillis());

    private int first() {
        System.out.println("执行第一个方法...");
        return random.nextInt(100);
    }

    private int second() {
        System.out.println("执行第二个方法...");
        return random.nextInt(100);
    }

    private int third() {
        System.out.println("执行第三个方法");
        return random.nextInt(100);
    }

    public void installWizard() {
        int first = this.first();
        if (first > 60) {
            int second = this.second();
            if (second > 60) {
                int third = this.third();
                if (third > 60) {
                    this.first();
                }
            }
        }
    }
}