package com.designpattern.chapter17_decorator_pattern.command03;

public class ConcreteComponent extends Component {

    @Override
    public void operate() {
        System.out.println("do something");
    }
}
