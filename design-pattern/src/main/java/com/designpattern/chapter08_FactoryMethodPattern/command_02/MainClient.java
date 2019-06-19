package com.designpattern.chapter08_FactoryMethodPattern.command_02;

public class MainClient {

    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct2.class);
        product.method2();
        /**
         * 业务处理
         */
    }
}
