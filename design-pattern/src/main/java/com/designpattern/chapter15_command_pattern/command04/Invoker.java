package com.designpattern.chapter15_command_pattern.command04;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public class Invoker {

    /**
     * what's command
     */
    private Command command;

    /**
     * the customer issue the command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * execute customer's command
     */
    public void action() {
        this.command.execute();
    }

}
