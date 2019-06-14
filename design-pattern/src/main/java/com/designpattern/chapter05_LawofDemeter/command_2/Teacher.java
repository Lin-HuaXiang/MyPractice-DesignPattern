package com.designpattern.chapter05_LawofDemeter.command_2;

public class Teacher {

    public void command(GroupLeader groupLeader) {
        groupLeader.countGirls();
    }
}
