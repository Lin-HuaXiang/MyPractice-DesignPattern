package com.designpattern.chapter11_BuilderPattern.command_04;

public class Director {

    private Builder builder = new ConcreteProduct();

    // 构建不同的产品
    public Product setAProduct() {
        // 设置产品组成内容，相当于拼装
        builder.setPart();
        /**
         * 设置不同的零件，产生不同的产品
         */
        return builder.buildProduct();
    }
}
