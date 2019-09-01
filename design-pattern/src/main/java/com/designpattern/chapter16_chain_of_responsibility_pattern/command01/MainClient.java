package com.designpattern.chapter16_chain_of_responsibility_pattern.command01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainClient {


    public static void main(String[] args) {
        Random random = new Random();
        List<IWomen> list = new ArrayList<>();
        for (int i =0; i < 5; i++) {
            list.add(new Women(random.nextInt(4), "我要出去逛街"));
        }
        IHandler father = new Father();
        IHandler husband = new Husband();
        IHandler son = new Son();
        for (IWomen women : list) {
            if (women.getType() == 1) {
                System.out.println("\n--- 女儿向父亲请示 ---");
                father.handleMessage(women);
            } else if (women.getType() == 2) {
                System.out.println("\n--- 妻子向丈夫请示 ---");
                husband.handleMessage(women);
            } else if (women.getType() == 3) {
                System.out.println("\n-- 母亲向儿子请示 ---");
                son.handleMessage(women);
            } else {
                // 暂时什么都不做
            }
        }


    }
}
