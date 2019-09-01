package com.designpattern.chapter08_factory_method_pattern.command_03;

import com.designpattern.chapter08_factory_method_pattern.command_01.Human;

/**
 * 简单工厂模式
 */
public class HumanFactory {

    /**
     * 改造为静态方法。
     * @param c
     * @param <T>
     * @return
     */
    public static <T extends Human> T createHuman(Class<T> c) {
        // 定义一个生产出的人种
        Human human = null;
        try {
            // 产生一个人种
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种生成错误");
        }
        return (T) human;
    }
}
