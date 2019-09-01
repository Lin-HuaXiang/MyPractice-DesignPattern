package com.designpattern.chapter33.command02;

public class Client {

    public static void main(String[] args) {
        // 定义出所谓的明星
        IStar freakStar = new FreakStar();
        // 看看他是怎么粉饰自己的
        // 演前吹嘘自己无所不能
        freakStar = new HotAir(freakStar);
        freakStar = new Deny(freakStar);
        System.out.println("看看一些虚假明星的形象");
        // 由于重复构造器并返回设置与其他构造器中，将freakStar进行迭代，因此重复多次act实现。
        freakStar.act();
    }
}
