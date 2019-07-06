package com.designpattern.chapter03_dependence_inversion_principle.entity.impl;

import com.designpattern.chapter03_dependence_inversion_principle.entity.ICar;

public class BMW implements ICar {

    public void run() {
        System.out.println("宝马汽车开始运行....");
    }
}
