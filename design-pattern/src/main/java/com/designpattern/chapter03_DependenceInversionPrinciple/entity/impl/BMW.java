package com.designpattern.chapter03_DependenceInversionPrinciple.entity.impl;

import com.designpattern.chapter03_DependenceInversionPrinciple.entity.ICar;

public class BMW implements ICar {

    public void run() {
        System.out.println("宝马汽车开始运行....");
    }
}
