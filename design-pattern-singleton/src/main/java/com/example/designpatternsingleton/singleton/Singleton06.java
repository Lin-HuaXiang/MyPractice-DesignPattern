package com.example.designpatternsingleton.singleton;

import java.util.concurrent.atomic.AtomicReference;

public class Singleton06 {

    private static final AtomicReference<Singleton06> INSTANCE = new AtomicReference<>();

    private static Singleton06 instance;

    private Singleton06() {

    }

    public static final Singleton06 getInstance() {
        for (;;) {
            Singleton06 instance = INSTANCE.get();
            if (null != instance) return instance;
            INSTANCE.compareAndSet(null, new Singleton06());
            return INSTANCE.get();
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton06.getInstance());
        System.out.println(Singleton06.getInstance());
    }




    
}
