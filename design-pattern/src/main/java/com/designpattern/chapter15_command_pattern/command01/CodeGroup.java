package com.designpattern.chapter15_command_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public class CodeGroup extends Group {

    /**
     * the customer asked the code group to go talk to them
     */
    @Override
    public void find() {
        System.out.println("find code group...");
    }

    /**
     * the customer asked for a new feature
     */
    @Override
    public void add() {
        System.out.println("the customer asked for a new feature");
    }

    /**
     * the customer asked for delete a feature
     */
    @Override
    public void delete() {
        System.out.println("the customer asked for delete a feature");
    }

    /**
     * the customer asked for change a feature
     */
    @Override
    public void change() {
        System.out.println("the customer asked for change a feature");
    }

    /**
     * the customer asked for the change of plan
     */
    @Override
    public void plan() {
        System.out.println("the customer asked for the code change of plan");
    }
}
