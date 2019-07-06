package com.designpattern.chapter11_builder_pattern.command_02;

import com.designpattern.chapter11_builder_pattern.command_01.BMWModel1;
import com.designpattern.chapter11_builder_pattern.command_01.BenzModel1;

import java.util.ArrayList;

public class MainClient2 {

    public static void main(String[] args) {
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");

        //要一个奔驰车
        BenzBuilder2 benzBuilder2 = new BenzBuilder2();
        benzBuilder2.setSequence(sequence);
        BenzModel1 benz = (BenzModel1) benzBuilder2.getCarModel();
        benz.run();

        BMWBuilder2 bmwBuilder2 = new BMWBuilder2();
        bmwBuilder2.setSequence(sequence);
        BMWModel1 bmw = (BMWModel1) bmwBuilder2.getCarModel();
        bmw.run();
    }
}
