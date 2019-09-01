package com.designpattern.chapter05_lawof_demeter.command_2;

public class Teacher {

    public void command(GroupLeader groupLeader) {
        groupLeader.countGirls();
    }
}
