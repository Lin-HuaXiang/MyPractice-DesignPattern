package com.designpattern.chapter08_factory_method_pattern.command_04;

import com.designpattern.chapter08_factory_method_pattern.command_01.Human;
import com.designpattern.chapter08_factory_method_pattern.command_01.WhiteHuman;

public class WhiteHumanFactory extends AbstractHumanFactory4 {

    @Override
    public Human createHuman() {
        return new WhiteHuman();
    }
}
