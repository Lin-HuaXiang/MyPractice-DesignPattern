package com.designpattern.chapter08_FactoryMethodPattern.command_01;

public class MainNvWa {

    public static void main(String[] args) {
        // 声明阴阳八卦炉
        AbstractHumanFactory yinYangLu = new HumanFactory();
        // 女娲第一次造人，火候不足，于是白人产生了
        System.out.println("\n-- 造出的第一批人是白色人种 --");
        Human whiteHuman = yinYangLu.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
        // 女娲第二次造人，火候过足，于是黑人产生了
        System.out.println("\n-- 造出的第二批是黑色人种 --");
        Human blackHuman = yinYangLu.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
        // 第三次造人，火候刚刚好，于是黄种人产生了
        System.out.println("\n-- 造出的第三批是黄色人种 --");
        Human yellowHuman = yinYangLu.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
