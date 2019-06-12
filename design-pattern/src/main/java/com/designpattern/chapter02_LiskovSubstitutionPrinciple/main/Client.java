package com.designpattern.chapter02_LiskovSubstitutionPrinciple.main;

import com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity.AUG;
import com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity.Father;
import com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity.Rifle;
import com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity.Snipper;
import com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity.Soldier;
import com.designpattern.chapter02_LiskovSubstitutionPrinciple.entity.Son;

import java.util.HashMap;

public class Client {

    public static void main1(String[] args) {
        Snipper sanMao = new Snipper();
//        sanMao.setAug(new AUG());
        sanMao.setAug((AUG)new Rifle());
        sanMao.killEnemy();

    }

    public static void main(String[] args) {
        invoker();

    }

    public static void invoker() {
//        Son f = new Son();
        Son f = new Son();
        HashMap map = new HashMap();
        f.doSomething(map);
    }

}
