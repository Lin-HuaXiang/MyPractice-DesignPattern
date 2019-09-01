package com.designpattern.chapter14_mediator_pattern.command03;

public class Colleague {

    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
