package com.designpattern.chapter09_AbstractFactoryPattern.command_02;

public abstract class AbstractCreator {

    // 创建A产品家族
    public abstract AbstractProductA createProductA();

    // 创建B产品家族
    public abstract AbstractProductB createProductB();
}
