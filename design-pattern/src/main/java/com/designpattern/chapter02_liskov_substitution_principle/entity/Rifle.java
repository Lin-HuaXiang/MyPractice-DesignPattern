package com.designpattern.chapter02_liskov_substitution_principle.entity;

public class Rifle extends AbstractGun{

    @Override
    public void shoot() {
        System.out.println("步枪射击...");
    }
}
