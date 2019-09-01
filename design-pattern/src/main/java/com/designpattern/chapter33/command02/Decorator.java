package com.designpattern.chapter33.command02;

public abstract class Decorator implements IStar {

    // 粉饰的是谁
    private IStar star;

    public Decorator(IStar star) {
        this.star = star;
    }

    public void act() {
        this.star.act();
    }
}
