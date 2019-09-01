package com.designpattern.chapter03_dependence_inversion_principle.entity.impl;

import com.designpattern.chapter03_dependence_inversion_principle.entity.ICar;
import com.designpattern.chapter03_dependence_inversion_principle.entity.IDriver;

/**
 * 1.构造函数传递依赖对象
 */
public class Driver2 implements IDriver {

    private ICar car;

    public Driver2(ICar _car) {
        this.car = _car;
    }

    public Driver2() { }

    @Override
    public void drive(ICar car) {
        this.car.run();
    }
}
