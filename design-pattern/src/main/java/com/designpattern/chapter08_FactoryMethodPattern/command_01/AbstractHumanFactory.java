package com.designpattern.chapter08_FactoryMethodPattern.command_01;

public abstract class AbstractHumanFactory {

    public abstract <T extends Human> T createHuman(Class<T> c);
}
