package com.designpattern.chapter15_command_pattern.command05;


public class ConcreteCommand1 extends Command {



    public ConcreteCommand1(Receiver receiver) {
        super(receiver);
    }

    /**
     * define a default receiver for itself
     */
    public ConcreteCommand1() {
        super(new ConcreteReceiver());
    }

    /**
     * each implement command class must implement a method of executing command
     */
    @Override
    void execute() {
        // business logic processing
        super.receiver.doSomething();
    }
}
