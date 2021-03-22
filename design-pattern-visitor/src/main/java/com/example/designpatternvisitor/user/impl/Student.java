package com.example.designpatternvisitor.user.impl;

import java.util.Random;

import com.example.designpatternvisitor.user.User;
import com.example.designpatternvisitor.visitor.Visitor;

import lombok.Data;

@Data
public class Student extends User {

    public Student(String name, String identity, String clazz) {
        super(name, identity, clazz);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int ranking() {
        return new Random().nextInt(100);
    }
    
}
