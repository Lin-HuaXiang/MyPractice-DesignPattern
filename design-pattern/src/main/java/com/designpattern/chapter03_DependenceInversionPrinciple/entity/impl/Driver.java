package com.designpattern.chapter03_DependenceInversionPrinciple.entity.impl;

import com.designpattern.chapter03_DependenceInversionPrinciple.entity.ICar;
import com.designpattern.chapter03_DependenceInversionPrinciple.entity.IDriver;

public class Driver implements IDriver {

    @Override
    public void drive(ICar car) {
        car.run();
    }
}
