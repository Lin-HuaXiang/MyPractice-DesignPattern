package com.example.designpatternsingleton.singleton;

public class Singleton02 {
    
    private static Singleton02 instance;

    private Singleton02() {

    }

    public static synchronized Singleton02 getInstance() {
        if (null != instance) return instance;
        instance = new Singleton02();
        return instance;
    }
}
