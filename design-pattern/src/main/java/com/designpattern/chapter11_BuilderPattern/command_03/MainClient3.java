package com.designpattern.chapter11_BuilderPattern.command_03;

public class MainClient3 {

    public static void main(String[] args) {
        Director3 director3 = new Director3();
        // 1万辆A型号的奔驰车
        for (int i = 0; i < 10000; i++) {
            director3.getABenzModel().run();
        }
        // 100辆B类型的奔驰车
        for (int i = 0; i < 100; i++) {
            director3.getBBenzModel().run();
        }
        // 1000辆C类型的宝马车
        for (int i = 0; i < 1000; i++) {
            director3.getCBMWModel().run();
        }
    }
}
