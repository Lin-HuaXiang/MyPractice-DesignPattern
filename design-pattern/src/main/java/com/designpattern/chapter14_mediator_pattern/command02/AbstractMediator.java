package com.designpattern.chapter14_mediator_pattern.command02;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public abstract class AbstractMediator {

    protected Purchase purchase;
    protected Sale sale;
    protected Stock stock;

    /**
     * constructor
     */
    public AbstractMediator() {
        this.purchase = new Purchase(this);
        this.sale = new Sale(this);
        this.stock = new Stock(this);
    }

    /**
     * The mediator's most important method is called the event method, which deal with relationships between multiple objects
     * @param str
     * @param objects
     */
    public abstract void execute(String str, Object... objects);
}
