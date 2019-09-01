package com.designpattern.chapter03_dependence_inversion_principle.entity.impl;

import com.designpattern.chapter03_dependence_inversion_principle.entity.ICar;
import com.designpattern.chapter03_dependence_inversion_principle.entity.IDriver;

public class Driver implements IDriver {

    @Override
    public void drive(ICar car) {
        car.run();
    }
}
