package com.designpattern.chapter10_template_method_pattern.command_03;

public class HummerH2Model3 extends HummerModel3 {

    @Override
    protected void start() {
        System.out.println("悍马H2发动...");
    }

    @Override
    protected void stop() {
        System.out.println("悍马H2停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("悍马H2鸣笛...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("悍马H2引擎声音是这样的...");
    }

    @Override
    protected boolean isAlarm() {
        // 默认没有喇叭
        return false;
    }

}
