package com.designpattern.chapter08_factory_method_pattern.command_04;

import com.designpattern.chapter08_factory_method_pattern.command_01.Human;
import com.designpattern.chapter08_factory_method_pattern.command_01.YellowHuman;

public class YellowHumanFactory extends AbstractHumanFactory4 {

    @Override
    public Human createHuman() {
        return new YellowHuman();
    }
}
