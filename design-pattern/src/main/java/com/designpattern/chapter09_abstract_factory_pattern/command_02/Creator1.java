package com.designpattern.chapter09_abstract_factory_pattern.command_02;

public class Creator1 extends AbstractCreator {

    // 只生产产品等级为1的A产品
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    // 只生产产品等级为1的B产品
    public AbstractProductB createProductB() {
        return new ProductB1();
    }

}
