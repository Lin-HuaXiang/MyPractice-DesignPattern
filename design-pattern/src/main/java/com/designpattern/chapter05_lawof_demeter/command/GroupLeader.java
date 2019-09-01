package com.designpattern.chapter05_lawof_demeter.command;

import java.util.List;

public class GroupLeader {

    public void countGrils(List<Girl> listGrils) {
        System.out.println("女生的数量是：" + listGrils.size());
    }

}
