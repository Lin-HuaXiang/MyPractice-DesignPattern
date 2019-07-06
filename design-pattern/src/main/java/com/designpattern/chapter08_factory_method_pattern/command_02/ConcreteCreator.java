package com.designpattern.chapter08_factory_method_pattern.command_02;

public class ConcreteCreator extends Creator {

    public <T extends Product> T createProduct(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            // 异常处理
        }
        return (T) product;
    }
}
