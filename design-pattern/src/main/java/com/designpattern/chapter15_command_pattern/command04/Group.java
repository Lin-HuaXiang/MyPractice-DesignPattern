package com.designpattern.chapter15_command_pattern.command04;

public abstract class Group {

    /**
     * party a and party b work separately, if you want to discuss with a group, you need find the group first.
     */
    public abstract void find();

    /**
     * asked to add feature
     */
    public abstract void add();

    /**
     * asked to delete feature
     */
    public abstract void delete();

    /**
     * asked to change feature
     */
    public abstract void change();

    /**
     * be required to present all change plans
     */
    public abstract void plan();

    /**
     *  each receiver can rollback commands that executed directly
     */
    public void rollBack() {
        System.out.println("the command roll back");
    }
}
