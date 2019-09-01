package com.designpattern.chapter13_prototype_pattern.command04;

public class ThingFour implements Cloneable {

    public ThingFour() {
        System.out.println("The constructor is executed");
    }

    @Override
    protected ThingFour clone() {
        ThingFour thingFour = null;
        try {
            thingFour = (ThingFour) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thingFour;
    }
}
