package com.designpattern.chapter15_command_pattern.command03;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public class Invoker {

    private Command command;

    /**
     * received the command
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * execute the command
     */
    public void action() {
        this.command.execute();
    }
}
