package com.designpattern.chapter02_liskov_substitution_principle.entity;

public class Snipper {

    private AUG aug;

    public void killEnemy() {
        aug.zoomOut();
        aug.shoot();
    }

    public AUG getAug() {
        return aug;
    }

    public void setAug(AUG aug) {
        this.aug = aug;
    }
}
