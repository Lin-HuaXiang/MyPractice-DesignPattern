package com.designpattern.chapter08_FactoryMethodPattern.command_04;

import com.designpattern.chapter08_FactoryMethodPattern.command_01.Human;

public class MainNvWa {

    public static void main(String[] args) {
        // 女娲第一次造人，火候不足，于是白色人种产生了
        System.out.println("-- 造出的第一批人是白色人种 --");
        Human whiteHuman = (new WhiteHumanFactory()).createHuman();
        whiteHuman.getColor();
        whiteHuman.talk();
        // 女娲第二次造人，火候过足，于是黑色人种产生了
        System.out.println("-- 造出的第二批人是黑色人种 --");
        Human blackHuman = (new BlackHumanFactory()).createHuman();
        blackHuman.getColor();
        blackHuman.talk();
        // 女娲第三次造人，火候刚刚好，于是黄色人种产生了
        System.out.println("-- 造出的第三批是黄色人种 --");
        Human yellowHuman = (new YellowHumanFactory()).createHuman();
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
