package com.designpattern.chapter17_decorator_pattern.command03;

public abstract class Decorator extends Component {

    private Component component = null;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operate() {
        // 用于super类是采用构造器传递被修饰者，被调用的是子类的实现方法，因此每次使用super.operate操作，都是上一个子类的实现方法，然后再到当前子类的实现方法。
        this.component.operate();
    }
}
