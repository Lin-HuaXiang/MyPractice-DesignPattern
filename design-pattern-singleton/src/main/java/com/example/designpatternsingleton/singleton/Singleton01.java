package com.example.designpatternsingleton.singleton;

public class Singleton01 {

    private static Singleton01 instance;

    private Singleton01() {}    

    public static Singleton01 getInstance() {
        if (null != instance) return instance;
        instance = new Singleton01();
        return instance;
    }
    
}
