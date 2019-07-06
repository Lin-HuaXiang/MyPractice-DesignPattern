package com.designpattern.chapter11_builder_pattern.command_04;

public abstract class Builder {

    // 设置产品的不同部分，以获得不同的产品
    public abstract void setPart();

    // 建造产品
    public abstract Product buildProduct();
}
