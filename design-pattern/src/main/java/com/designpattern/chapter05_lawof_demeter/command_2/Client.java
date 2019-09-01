package com.designpattern.chapter05_lawof_demeter.command_2;

import com.designpattern.chapter05_lawof_demeter.command.Girl;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<Girl> listGirls = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listGirls.add(new Girl());
        }
        com.designpattern.chapter05_lawof_demeter.command_2.Teacher teacher
                = new com.designpattern.chapter05_lawof_demeter.command_2.Teacher();
        teacher.command(new com.designpattern.chapter05_lawof_demeter.command_2.GroupLeader(listGirls));
    }
}
