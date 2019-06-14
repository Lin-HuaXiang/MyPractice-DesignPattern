package com.designpattern.chapter05_LawofDemeter.command_2;

import com.designpattern.chapter05_LawofDemeter.command.Girl;
import com.designpattern.chapter05_LawofDemeter.command.GroupLeader;
import com.designpattern.chapter05_LawofDemeter.command.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<Girl> listGirls = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listGirls.add(new Girl());
        }
        com.designpattern.chapter05_LawofDemeter.command_2.Teacher teacher
                = new com.designpattern.chapter05_LawofDemeter.command_2.Teacher();
        teacher.command(new com.designpattern.chapter05_LawofDemeter.command_2.GroupLeader(listGirls));
    }
}
