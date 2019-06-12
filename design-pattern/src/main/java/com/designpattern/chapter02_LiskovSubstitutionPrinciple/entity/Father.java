package com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Father {

//    public Collection doSomething(HashMap map) {
//        System.out.println("父类被执行...");
//        return map.values();
//    }

    public Collection doSomething(Map map) {
        System.out.println("父类被执行...");
        return map.values();
    }
}
