package com.example.designpatternvisitor.user.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.designpatternvisitor.user.User;
import com.example.designpatternvisitor.visitor.Visitor;

import lombok.Data;

@Data
public class Teacher extends User {

    public Teacher(String name, String identity, String clazz) {
        super(name, identity, clazz);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public double entranceRatio() {
        return BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.UP).doubleValue();
    }

}
