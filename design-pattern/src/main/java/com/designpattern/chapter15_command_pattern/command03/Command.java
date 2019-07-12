package com.designpattern.chapter15_command_pattern.command03;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public abstract class Command {

    /**
     * each command class must have a method to execute to command
     */
    abstract void execute();
}
