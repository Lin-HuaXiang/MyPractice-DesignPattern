package com.designpattern.chapter03_DependenceInversionPrinciple.main;

import com.designpattern.chapter03_DependenceInversionPrinciple.entity.ICar;
import com.designpattern.chapter03_DependenceInversionPrinciple.entity.IDriver;
import com.designpattern.chapter03_DependenceInversionPrinciple.entity.impl.BMW;
import com.designpattern.chapter03_DependenceInversionPrinciple.entity.impl.Benz;
import com.designpattern.chapter03_DependenceInversionPrinciple.entity.impl.Driver;

public class Client {

//    public static void main(String[] args) {
//        Driver zhangsan = new Driver();
//        Benz benz = new Benz();
//        zhangsan.drive(benz);
//    }

//    public static void main(String[] args) {
//        IDriver zhangsan = new Driver();
//        ICar benz = new Benz();
//        zhangsan.drive(benz);
//
//    }

    public static void main(String[] args) {
        IDriver zhangsan = new Driver();
        ICar bmw = new BMW();
        zhangsan.drive(bmw);
    }
}
