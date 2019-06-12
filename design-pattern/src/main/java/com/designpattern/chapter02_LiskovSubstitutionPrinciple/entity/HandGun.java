package com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity;

public class HandGun extends AbstractGun {

    @Override
    public void shoot() {
        System.out.println("手枪射击...");
    }
}
