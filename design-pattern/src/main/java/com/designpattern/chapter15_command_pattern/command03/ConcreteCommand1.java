package com.designpattern.chapter15_command_pattern.command03;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public class ConcreteCommand1 extends Command {

    /**
     * which receiver class is to be used for command processing
     */
    private Receiver receiver;


    /**
     * Constructor passes the receiver
     */
    public ConcreteCommand1(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * there are must implemented a command
     */
    @Override
    void execute() {
        // business logic
        this.receiver.doSomething();
    }
}
