package com.designpattern.chapter05_lawof_demeter.command;

public class Client {

    public static void main1(String[] args) {
        Teacher teacher = new Teacher();
        teacher.commond(new GroupLeader());
    }
}
