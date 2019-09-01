package com.designpattern.chapter14_mediator_pattern.command03;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public abstract class Mediator {

    /**
     * define concrete colleague class
     */
    protected ConcreteColleague1 c1;
    protected ConcreteColleague2 c2;

    public ConcreteColleague1 getC1() {
        return c1;
    }

    public void setC1(ConcreteColleague1 c1) {
        this.c1 = c1;
    }

    public ConcreteColleague2 getC2() {
        return c2;
    }

    public void setC2(ConcreteColleague2 c2) {
        this.c2 = c2;
    }

    /**
     * mediator's business logic
     */
    public abstract void doSomething1();

    /**
     * mediator's business logic
     */
    public abstract void doSomething2();
}
