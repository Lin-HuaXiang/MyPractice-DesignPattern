package com.designpattern.chapter17_decorator_pattern.command03;

public class ConcreteDecorator3 extends Decorator {

    public ConcreteDecorator3(Component component) {
        super(component);
    }

    private void method3() {
        System.out.println("method3 修饰");
    }

    @Override
    public void operate() {
        super.operate();
        this.method3();
    }
}
