package com.designpattern.chapter33.command02;

public class Deny extends Decorator {

    public Deny(IStar star) {
        super(star);
    }

    @Override
    public void act() {
        super.act();
        System.out.println("演后：百般抵懒，死不承认");
    }
}
