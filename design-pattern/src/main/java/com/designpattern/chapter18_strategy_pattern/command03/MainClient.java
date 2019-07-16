package com.designpattern.chapter18_strategy_pattern.command03;

public class MainClient {

    public static void main(String[] args) {
        int a = Integer.parseInt("1");
        String symbol = " + ";
        int b = Integer.parseInt("2");
        System.out.println(a + symbol + b);
        System.out.println(Calculator.ADD.exec(a, b));
    }
}
