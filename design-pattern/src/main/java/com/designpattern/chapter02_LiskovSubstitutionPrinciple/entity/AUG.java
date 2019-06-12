package com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity;

public class AUG extends Rifle {

    public void zoomOut() {
        System.out.println("通过望远镜查看敌人");
    }

    public void shoot() {
        System.out.println("AUG射击");
    }
}
