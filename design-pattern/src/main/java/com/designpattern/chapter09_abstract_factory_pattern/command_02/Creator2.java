package com.designpattern.chapter09_abstract_factory_pattern.command_02;

public class Creator2 extends AbstractCreator {

    // 只生产产品等级为2的A产品
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    // 只生产产品等级为2的B产品
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
