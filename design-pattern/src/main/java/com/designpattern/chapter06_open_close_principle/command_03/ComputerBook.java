package com.designpattern.chapter06_open_close_principle.command_03;

public class ComputerBook implements IComputerBook {

    private String name;

    private int price;

    private String scope;

    private String author;

    public ComputerBook() {

    }

    public ComputerBook(String name, int price, String scope, String author) {
        this.name = name;
        this.price = price;
        this.scope = scope;
        this.author = author;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
