package com.designpattern.chapter15_command_pattern.command04;

public class CancelDeletePageCommand extends Command {

    /**
     * undo a command to delete a page
     */
    @Override
    public void execute() {
        super.pg.rollBack();
    }
}
