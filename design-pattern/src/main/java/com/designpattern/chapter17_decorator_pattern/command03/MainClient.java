package com.designpattern.chapter17_decorator_pattern.command03;

public class MainClient {

    public static void main(String[] args) {

        // component变成了concreteComponent
        Component component = new ConcreteComponent();
        // component变成了concreteDecorator1
        component = new ConcreteDecorator1(component);
        // component变成了concreteDecorator1
        component = new ConcreteDecorator2(component);
        // component变成了concreteDecorator1
        component = new ConcreteDecorator3(component);
        // 三个component的地址不是同一个，因此子类实现方法都执行，他们不会相互影响，都是指针的指向
        // super.operator是指代父类的方法，但是父类的却是调用了传递过来的参数的operator，才能做到上一个子类实现方法的调用
        component.operate();
    }
}
