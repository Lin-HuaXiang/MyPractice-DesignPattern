package com.designpattern.chapter15_command_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public class PageGroup extends Group {

    /**
     * First of all, the page group should be able to find it. Who else you like to talk to?
     */
    @Override
    public void find() {
        System.out.println("find the page group");
    }

    /**
     * The customer asked for an addition
     */
    @Override
    public void add() {
        System.out.println("the customer asked for a new page");
    }

    /**
     * The customer asked for delete some pages
     */
    @Override
    public void delete() {
        System.out.println("the customer delete a page");
    }

    /**
     * The customer asked for change to existing pages
     */
    @Override
    public void change() {

    }

    /**
     * All of add, delete or change operation, need to a plan
     */
    @Override
    public void plan() {
        System.out.println("the customer asked for the page change of plan");
    }
}
