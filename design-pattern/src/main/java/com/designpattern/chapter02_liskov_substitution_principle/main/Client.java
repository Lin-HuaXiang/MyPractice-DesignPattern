package com.designpattern.chapter02_liskov_substitution_principle.main;

import com.designpattern.chapter02_liskov_substitution_principle.entity.AUG;
import com.designpattern.chapter02_liskov_substitution_principle.entity.Rifle;
import com.designpattern.chapter02_liskov_substitution_principle.entity.Snipper;
import com.designpattern.chapter02_liskov_substitution_principle.entity.Son;

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
