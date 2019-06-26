package com.designpattern.chapter11_BuilderPattern.command_02;

import com.designpattern.chapter11_BuilderPattern.command_01.BMWModel1;
import com.designpattern.chapter11_BuilderPattern.command_01.CarModel1;

import java.util.ArrayList;

/**
 * 相当于每个builder类有个实例，然后里面设置的内容需要setSequence进行传递，才能构造出对应顺序的车子
 */
public class BMWBuilder2 extends CarBuilder2 {

    private BMWModel1 bmw = new BMWModel1();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);
    }

    @Override
    public CarModel1 getCarModel() {
        return this.bmw;
    }
}
