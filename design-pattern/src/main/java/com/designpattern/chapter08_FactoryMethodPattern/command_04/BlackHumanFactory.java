package com.designpattern.chapter08_FactoryMethodPattern.command_04;

import com.designpattern.chapter08_FactoryMethodPattern.command_01.AbstractHumanFactory;
import com.designpattern.chapter08_FactoryMethodPattern.command_01.BlackHuman;
import com.designpattern.chapter08_FactoryMethodPattern.command_01.Human;

public class BlackHumanFactory extends AbstractHumanFactory4 {

    @Override
    public Human createHuman() {
        return new BlackHuman();
    }
}
