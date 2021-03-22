package com.example.designpatternvisitor.user;

import com.example.designpatternvisitor.visitor.Visitor;

import lombok.Data;

@Data
public abstract class User {

    private String name;
    private String identity;
    private String clazz;

    protected User(String name, String identity, String clazz) {
        this.name = name;
        this.identity = identity;
        this.clazz = clazz;
    }

    // 核心访问方法
    public abstract void accept(Visitor visitor);

}
