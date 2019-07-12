package com.designpattern.chapter15_command_pattern.command05;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public class MainClient {

    /**
     * main => invoker => command => receiver(group)
     * @param args
     */
    public static void main(String[] args) {
        // firstly, declare the invoker
        Invoker invoker = new Invoker();
        // define a command to send to receiver
        Command command = new ConcreteCommand1();
        // give to command to the invoker to execute
        invoker.setCommand(command);
        invoker.action();
    }
}
