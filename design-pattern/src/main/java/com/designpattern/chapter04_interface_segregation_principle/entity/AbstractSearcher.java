package com.designpattern.chapter04_interface_segregation_principle.entity;

public abstract class AbstractSearcher {

    protected IPettyGirl pettyGirl;

    public AbstractSearcher(IPettyGirl _pettyGirl) {
        this.pettyGirl = _pettyGirl;
    }

    public abstract void show();  // 抽象方法不是默认公开
}
