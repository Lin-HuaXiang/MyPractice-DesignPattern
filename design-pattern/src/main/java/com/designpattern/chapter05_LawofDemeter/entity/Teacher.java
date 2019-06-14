package com.designpattern.chapter05_LawofDemeter.entity;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

    public void commond(GroupLeader groupLeader) {
        List listGirls = new ArrayList();
        for (int i = 0; i < 20; i++) {
            listGirls.add(new Girl());
        }
        groupLeader.countGrils(listGirls);
    }
}
