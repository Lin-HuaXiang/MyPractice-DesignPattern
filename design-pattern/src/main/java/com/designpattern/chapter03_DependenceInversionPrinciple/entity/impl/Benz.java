package com.designpattern.chapter03_DependenceInversionPrinciple.entity.impl;

import com.designpattern.chapter03_DependenceInversionPrinciple.entity.ICar;

public class Benz implements ICar {

    public void run() {
        System.out.println("奔驰汽车开始运行...");
    }
}
