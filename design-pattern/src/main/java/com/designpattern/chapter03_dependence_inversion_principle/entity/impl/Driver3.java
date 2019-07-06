package com.designpattern.chapter03_dependence_inversion_principle.entity.impl;

import com.designpattern.chapter03_dependence_inversion_principle.entity.ICar;
import com.designpattern.chapter03_dependence_inversion_principle.entity.IDriver1;

/**
 * 2.Setter方法传递依赖对象
 */
public class Driver3 implements IDriver1 {

    private ICar car;

    public void setCar(ICar car) {
        this.car = car;
    }

    public void drive() {
        this.car.run();
    }


}
