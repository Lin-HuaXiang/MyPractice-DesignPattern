package com.designpattern.chapter14_mediator_pattern.command03;

public class ConcreteColleague1 extends Colleague {

    // send mediator through the constructor


    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    /**
     * self-method
     */
    public void selfMethod() {
        // handle your own business logic
    }

    /**
     * dep-method
     */
    public void depMethod() {
        // handle your own business logic

        // business logic that cannot be handled by yourself is delegated to mediator
        super.mediator.doSomething1();
    }
}
