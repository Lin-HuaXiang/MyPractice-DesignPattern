package com.designpattern.chapter09_abstract_factory_pattern.command_02;

public abstract class AbstractCreator {

    // 创建A产品家族
    public abstract AbstractProductA createProductA();

    // 创建B产品家族
    public abstract AbstractProductB createProductB();
}
