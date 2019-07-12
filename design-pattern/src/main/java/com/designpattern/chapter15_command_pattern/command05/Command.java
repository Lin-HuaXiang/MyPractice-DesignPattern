package com.designpattern.chapter15_command_pattern.command05;

public abstract class Command {

    /**
     * define a static object for  a subclass
     */
    protected final Receiver receiver;

    /**
     * the implementation class must define a receiver
     * @param receiver
     */
    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * each command class must have a method for executing command
     */
    abstract void execute();

}
