package com.designpattern.chapter05_lawof_demeter.command_2;

import com.designpattern.chapter05_lawof_demeter.command.Girl;

import java.util.List;

public class GroupLeader {

    private List<Girl> listGirls;

    public GroupLeader(List<Girl> listGirls) {
        this.listGirls = listGirls;
    }

    public void countGirls() {
        System.out.println("女生的数量是：" + this.listGirls.size());
    }
}
