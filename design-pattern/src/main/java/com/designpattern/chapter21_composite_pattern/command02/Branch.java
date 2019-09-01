package com.designpattern.chapter21_composite_pattern.command02;

import java.util.ArrayList;
import java.util.List;

public class Branch extends Corp {

    private List<Corp> subordinateList = new ArrayList<>();

    Branch(String name, String position, int salary) {
        super(name, position, salary);
    }

    void addSubordinate(Corp corp) {
        this.subordinateList.add(corp);
    }

    List<Corp> getSubordinate() {
        return this.subordinateList;
    }
}
