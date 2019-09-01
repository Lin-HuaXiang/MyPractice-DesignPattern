package com.designpattern.chapter15_command_pattern.command02;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public class DeletePageCommand extends Command {

    /**
     * execute a delete page command
     */
    @Override
    public void execute() {
        // find the page
        super.pg.find();
        // delete a page
        super.rg.delete();
        // given a plan
        super.rg.plan();
    }
}
