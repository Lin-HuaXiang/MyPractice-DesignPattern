package com.designpattern.chapter15_command_pattern.command03;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public class MainClient {


    public static void main(String[] args) {
        // firstly, declare the invoker
        Invoker invoker = new Invoker();
        // define receiver
        Receiver receiver = new ConcreteReceiver();
        // define a command to send to the receiver
        Command command = new ConcreteCommand1(receiver);
        // give the command to the invoker to execute
        invoker.setCommand(command);
        invoker.action();
    }
}
