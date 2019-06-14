package com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.impl;

import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.IGoodBodyGril;
import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.IGreatTemperamentGirl;
import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.IPettyGirl;

public class PettyGirl implements IGoodBodyGril, IGreatTemperamentGirl {  // IPettyGirl拆分为IGoodBodyGril,IGreatTemperamentGirl

    private String name;

    public PettyGirl(String _name) {
        this.name = _name;
    }

    @Override
    public void goodLooking() {
        System.out.println(this.name + "---脸蛋很漂亮");
    }

    @Override
    public void niceFigure() {
        System.out.println(this.name + "---气质非常好");
    }

    @Override
    public void greatTemperament() {
        System.out.println(this.name + "---身材非常棒");
    }
}
