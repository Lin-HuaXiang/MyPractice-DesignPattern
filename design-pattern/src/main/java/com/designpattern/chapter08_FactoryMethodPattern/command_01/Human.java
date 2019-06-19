package com.designpattern.chapter08_FactoryMethodPattern.command_01;

public interface Human {

    // 每个人种的皮肤都应该有相应的颜色
    void getColor();

    // 人类会说话
    void talk();
}
