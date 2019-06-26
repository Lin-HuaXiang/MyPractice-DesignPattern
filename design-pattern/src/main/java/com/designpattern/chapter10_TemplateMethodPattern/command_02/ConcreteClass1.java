package com.designpattern.chapter10_TemplateMethodPattern.command_02;

public class ConcreteClass1 extends AbstractClass {

    @Override
    protected void doSomething() {

    }

    @Override
    protected void doAnything() {

    }

    @Override
    public void templateMethod() {
        super.templateMethod();
    }
}
