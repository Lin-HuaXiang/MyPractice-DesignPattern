package com.designpattern.chapter15_command_pattern.command04;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public class AddRequirementCommand extends Command {

    /**
     * execute the command to add a requirement
     */
    @Override
    public void execute() {
        // find the requirement group
        super.rg.find();
        // add a requirement
        super.rg.add();
        // add a page
        super.pg.add();
        // add a feature
        super.cg.add();
        // plan are given
        super.rg.plan();
    }
}
