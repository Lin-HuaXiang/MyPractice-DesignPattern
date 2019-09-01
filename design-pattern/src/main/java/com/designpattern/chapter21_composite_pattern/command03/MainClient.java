package com.designpattern.chapter21_composite_pattern.command03;

public class MainClient {

    public static void main(String[] args) {
        Composite root = new Composite();
        root.doSomething();
        Composite branch = new Composite();
        Leaf leaf = new Leaf();
        root.add(branch);
        branch.add(leaf);
    }

    public static void display(Composite root) {
        for (Component component : root.getChildren()) {
            if (component instanceof Leaf) {
                ((Leaf) component).doSomething();
            } else {
                // 如果不是叶子节点，进行递归
                display((Composite) component);
            }
        }
    }




}
