package com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Son extends Father {

//    // 不能使用Override 方法签名不一致
//    public Collection doSomething(Map map) {
//        System.out.println("子类被执行");
//        return map.values();
//    }

    // 不能使用Override 方法签名不一致
    public Collection doSomething(HashMap map) {
        System.out.println("子类被执行");
        return map.values();
    }
}
