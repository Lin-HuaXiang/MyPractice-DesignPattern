package com.designpattern.chapter08_factory_method_pattern.command_03;

import com.designpattern.chapter08_factory_method_pattern.command_01.BlackHuman;
import com.designpattern.chapter08_factory_method_pattern.command_01.Human;
import com.designpattern.chapter08_factory_method_pattern.command_01.WhiteHuman;
import com.designpattern.chapter08_factory_method_pattern.command_01.YellowHuman;

public class MainNvWa {

    public static void main(String[] args) {
        // 女娲第一次造人，火候不足，于是白色人种产生
        System.out.println("-- 造出的第一批是白色人种 --");
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
        // 女娲第二次造人，火候过足，于是黑色人种产生了
        System.out.println("-- 造出的第二批是黑色人种 --");
        Human blackMan = HumanFactory.createHuman(BlackHuman.class);
        blackMan.getColor();
        blackMan.talk();
        // 女娲第三次造人，火候刚刚好，于是黄色人种产生了
        System.out.println("-- 造出的第三批是黄色人种 --");
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
