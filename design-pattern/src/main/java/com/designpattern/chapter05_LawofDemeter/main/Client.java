package com.designpattern.chapter05_LawofDemeter.main;

import com.designpattern.chapter05_LawofDemeter.entity.GroupLeader;
import com.designpattern.chapter05_LawofDemeter.entity.Teacher;

public class Client {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.commond(new GroupLeader());
    }
}
