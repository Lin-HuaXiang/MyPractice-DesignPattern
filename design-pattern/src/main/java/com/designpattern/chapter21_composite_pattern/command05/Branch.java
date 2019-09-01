package com.designpattern.chapter21_composite_pattern.command05;

import java.util.ArrayList;
import java.util.List;

public class Branch extends Corp {

    private List<Corp> subordinateList = new ArrayList<>();

    Branch(String name, String position, int salary) {
        super(name, position, salary);
    }

    void addSubordinate(Corp corp) {
        // 当添加一个子节点的时候，将当前节点作为子节点的父节点
        corp.setParent(this);
        this.subordinateList.add(corp);
    }

    List<Corp> getSubordinate() {
        return this.subordinateList;
    }
}
