package com.designpattern.chapter10_template_method_pattern.command_01;

public class HummerH1Model extends HummerModel {

    // H1型号的悍马车鸣笛
    public void alarm() {
        System.out.println("悍马H1鸣笛");
    }

    // 引擎轰鸣声
    public void engineBoom() {
        System.out.println("悍马H1引擎声是这样的");
    }

    // 汽车发动
    public void start() {
        System.out.println("悍马H1发动...");
    }

    // 停车
    public void stop() {
        System.out.println("悍马H1停车");
    }


}
