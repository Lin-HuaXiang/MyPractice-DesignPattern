package com.designpattern.chapter17_decorator_pattern.command03;

public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    private void method1() {
        System.out.println("method1 修饰");
    }

    @Override
    public void operate() {
        super.operate();
        this.method1();
    }
}
