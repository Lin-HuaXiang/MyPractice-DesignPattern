package com.example.designpatternsingleton.singleton;

public class Singleton04 {

    private static class SingletonHolder {
        private static Singleton04 instance = new Singleton04();
    }

    private Singleton04() {

    }

    public static Singleton04 getInstance() {
        return SingletonHolder.instance;
    }
    
}
