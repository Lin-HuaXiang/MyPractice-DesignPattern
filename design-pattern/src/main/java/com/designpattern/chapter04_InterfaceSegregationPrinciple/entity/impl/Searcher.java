package com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.impl;

import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.AbstractSearcher;
import com.designpattern.chapter04_InterfaceSegregationPrinciple.entity.IPettyGirl;

//public class Searcher extends AbstractSearcher {
//
//    public Searcher(IPettyGirl _pettyGirl) {
//        super(_pettyGirl);
//    }
//
//    public a void show() {
//        super.pettyGirl.goodLooking();
//        super.pettyGirl.niceFigure();
//        super.pettyGirl.greatTemperament();
//    }
//}

public class Searcher extends AbstractSearcher{
    public Searcher(IPettyGirl _pettyGirl){
        super(_pettyGirl);
    }
    //展示美女的信息
    public void show(){
        System.out.println("--------美女的信息如下：---------------");
        //展示面容
        super.pettyGirl.goodLooking();
        //展示身材
        super.pettyGirl.niceFigure();
        //展示气质
        super.pettyGirl.greatTemperament();
    }
}