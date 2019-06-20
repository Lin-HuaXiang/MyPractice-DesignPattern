package com.designpattern.chapter09_AbstractFactoryPattern.commond_01;

public interface HumanFactory {
    // 制造一个黄色人种
    Human createYellowHuman();

    // 制造一个白色人种
    Human createWhiteHuman();

    // 制造一个黑色人种
    Human creatBlackHuman();
}
