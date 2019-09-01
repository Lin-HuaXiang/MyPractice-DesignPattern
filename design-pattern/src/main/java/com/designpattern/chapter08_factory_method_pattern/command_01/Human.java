package com.designpattern.chapter08_factory_method_pattern.command_01;

public interface Human {

    // 每个人种的皮肤都应该有相应的颜色
    void getColor();

    // 人类会说话
    void talk();
}
