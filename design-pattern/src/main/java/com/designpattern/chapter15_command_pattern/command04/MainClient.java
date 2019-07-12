package com.designpattern.chapter15_command_pattern.command04;

public class MainClient {


    public static void main(String[] args) {
        // define our invoker
        Invoker xiaoSan = new Invoker();
        // the customer asked for undo delete a page
        System.out.println("--- the customer asked for undo delete a page ---");
        // the customer gave us a command
        Command command = new CancelDeletePageCommand();
        // the invoker received th command
        xiaoSan.setCommand(command);
        // the invoker execute the command
        xiaoSan.action();
    }
}
