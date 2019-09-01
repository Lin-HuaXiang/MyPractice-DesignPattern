package com.designpattern.chapter14_mediator_pattern.command03;

public class ConcreteColleague2 extends Colleague {

    // send mediator through the constructor


    public ConcreteColleague2(Mediator mediator) {
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
        super.mediator.doSomething2();
    }
}
