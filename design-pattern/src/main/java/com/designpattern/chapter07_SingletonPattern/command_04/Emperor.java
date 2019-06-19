package com.designpattern.chapter07_SingletonPattern.command_04;

import java.util.ArrayList;
import java.util.Random;

public class Emperor {

    // 定义最多能产生的实例数量
    private static int MAX_NUM_OF_EMPEROR = 2;

    // 每个皇帝都有名字， 使用一个ArrayList来容纳，每个对象的私有属性
    private static ArrayList<String> nameList = new ArrayList<>();

    // 定义一个列表，容纳所有的皇帝实例
    private static ArrayList<Emperor> emperorLists = new ArrayList<>();

    // 当前皇帝序列号
    private static int countNumOfEmperor = 0;

    // 产生的所有对象
    static {
        for (int i = 0; i < MAX_NUM_OF_EMPEROR; i++) {
            emperorLists.add(new Emperor("皇" + (i + 1) + "帝"));
        }
    }

    public Emperor() {

    }

    // 传入名称，建立一个皇帝对象
    private Emperor(String name) {
        nameList.add(name);
    }

    //随机获得一个皇帝对象
    public static Emperor getInstance() {
        Random random = new Random();
        // 随机拉出一个皇帝，只要是一个精神领袖就成
        countNumOfEmperor = random.nextInt(MAX_NUM_OF_EMPEROR);
        return emperorLists.get(countNumOfEmperor);
    }

    // 皇帝发话了
    public static void say() {
        System.out.println(nameList.get(countNumOfEmperor));
    }

}
