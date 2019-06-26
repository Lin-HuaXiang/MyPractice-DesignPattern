package com.designpattern.chapter11_BuilderPattern.command_02;

import com.designpattern.chapter11_BuilderPattern.command_01.BenzModel1;
import com.designpattern.chapter11_BuilderPattern.command_01.CarModel1;

import java.util.ArrayList;

public class BenzBuilder2 extends CarBuilder2 {

    private BenzModel1 benz = new BenzModel1();

    public CarModel1 getCarModel() {
        return this.benz;
    }

    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }
}
