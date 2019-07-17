package com.designpattern.chapter21_composite_pattern.command02;

public abstract class Corp {

    private String name;

    private String position;

    private int salary;

    public Corp(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getInfo() {
        return  "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary;
    }

}
