package com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity;

public class Soldier {

    private AbstractGun gun;

    public void setGun(AbstractGun _gun) {
        this.gun = _gun;
    }

    public void killEnemy() {
        System.out.println("士兵开始杀敌人...");
        gun.shoot();
    }

}
