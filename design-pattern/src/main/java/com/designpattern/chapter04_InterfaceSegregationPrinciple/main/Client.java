package com.designpattern.chapter04_InterfaceSegregationPrinciple.main;

import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.AbstractSearcher;
import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.IPettyGirl;
import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.impl.PettyGirl;
import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.impl.Searcher;

public class Client {

    public static void main(String[] args) {
        IPettyGirl pettyGirl = new PettyGirl("嫣嫣");
        AbstractSearcher searcher = new Searcher(pettyGirl);
        searcher.show();
    }
}
