package com.example.designpatternsingleton.singleton;

public class Singleton05 {

    private static Singleton05 instance;

    private Singleton05() {

    }

    public static Singleton05 getInstance() {
        if (null != instance) return instance;
        synchronized(Singleton05.class) {
            if (null == instance) {
                instance = new Singleton05();
            }
        }
        return instance;
    }
    
}
