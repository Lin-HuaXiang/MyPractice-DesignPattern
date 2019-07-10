package com.designpattern.chapter15_command_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public class RequirementGroup extends Group {

    /**
     * the customer asked the requirement group to go talk to them.
     */
    @Override
    public void find() {
        System.out.println("find the requirement group...");
    }

    /**
     * the customer asked for an addition
     */
    @Override
    public void add() {
        System.out.println("the customer asked for an addition");
    }

    /**
     * the customer asked for modify a requirement
     */
    @Override
    public void delete() {
        System.out.println("the customer asked for modify a requirement");
    }

    /**
     * the customer asked for delete a requirement
     */
    @Override
    public void change() {
        System.out.println("the customer asked for delete a requirement");
    }

    /**
     * the customer asked for the change of plan
     */
    @Override
    public void plan() {
        System.out.println("the customer asked for the change of plan");
    }
}
