package com.designpattern.chapter11_builder_pattern.command_01;

import java.util.ArrayList;

public class MainClient1 {

    public static void main(String[] args) {
        BenzModel1 benz = new BenzModel1();
        // 存放run的顺序
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        // 我们把这个顺序赋予奔驰车
        benz.setSequence(sequence);
        benz.run();
    }
}
