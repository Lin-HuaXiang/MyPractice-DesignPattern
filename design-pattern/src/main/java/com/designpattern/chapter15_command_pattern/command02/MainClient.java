package com.designpattern.chapter15_command_pattern.command02;

public class MainClient {

    public static void main(String[] args) {
        // define our invoker
        Invoker xiaoSan = new Invoker();
        // the customer asked for a addition
        System.out.println("--- the customer asked for a addition ---");
        // the customer gave us a command
        Command command = new AddRequirementCommand();
        // the invoker received the command
        xiaoSan.setCommand(command);
        // the invoker execute the command
        xiaoSan.action();
    }

    public static void main2(String[] args) {
        // define our invoker
        Invoker xiaoSan = new Invoker();
        // the customer asked for delete a page
        System.out.println("--- the customer asked for delete a page ---");
        // the customer gave us a command
        Command command = new DeletePageCommand();
        // the invoker received th command
        xiaoSan.setCommand(command);
        // the invoker execute the command
        xiaoSan.action();
    }
}
