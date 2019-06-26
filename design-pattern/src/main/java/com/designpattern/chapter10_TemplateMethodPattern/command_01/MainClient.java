package com.designpattern.chapter10_TemplateMethodPattern.command_01;

public class MainClient {

    public static void main(String[] args) {
        // xx公司要H1型号的悍马
        HummerModel h1 = new HummerH1Model();
        // H1模型演示
        h1.run();
    }
}
