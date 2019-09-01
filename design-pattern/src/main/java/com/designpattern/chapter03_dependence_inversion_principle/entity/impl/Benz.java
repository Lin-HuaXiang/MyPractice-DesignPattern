package com.designpattern.chapter03_dependence_inversion_principle.entity.impl;

import com.designpattern.chapter03_dependence_inversion_principle.entity.ICar;

public class Benz implements ICar {

    public void run() {
        System.out.println("奔驰汽车开始运行...");
    }
}
