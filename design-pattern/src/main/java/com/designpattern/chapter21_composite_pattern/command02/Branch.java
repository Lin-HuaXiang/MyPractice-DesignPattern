package com.designpattern.chapter21_composite_pattern.command02;

import java.util.ArrayList;
import java.util.List;

public class Branch extends Corp {

    List<Corp> subordinateList = new ArrayList<>();

    public Branch(String name, String position, int salary) {
        super(name, position, salary);
    }

    public void addSubordinate(Corp corp) {
        this.subordinateList.add(corp);
    }

    public List<Corp> getSubordinate() {
        return this.subordinateList;
    }
}
