package com.designpattern.chapter07_singleton_pattern.command_03;

/**
 * 线程不安全
 */
public class Singleton {

    private static Singleton singleton = null;

    // 限制产生多个对象
    private Singleton() {

    }

    // 通过该方法获得实例对象
    public static Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
