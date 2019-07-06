package com.designpattern.chapter08_factory_method_pattern.command_04;

import com.designpattern.chapter08_factory_method_pattern.command_01.BlackHuman;
import com.designpattern.chapter08_factory_method_pattern.command_01.Human;

public class BlackHumanFactory extends AbstractHumanFactory4 {

    @Override
    public Human createHuman() {
        return new BlackHuman();
    }
}
