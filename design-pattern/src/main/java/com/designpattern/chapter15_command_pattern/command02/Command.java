package com.designpattern.chapter15_command_pattern.command02;

import com.designpattern.chapter15_command_pattern.command01.CodeGroup;
import com.designpattern.chapter15_command_pattern.command01.PageGroup;
import com.designpattern.chapter15_command_pattern.command01.RequirementGroup;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public abstract class Command {
    /**
     * defined three groups, subclass can be used directly
     */
    protected RequirementGroup rg = new RequirementGroup();
    protected PageGroup pg = new PageGroup();
    protected CodeGroup cg = new CodeGroup();

    /**
     * Just on way, what do you want me to do
     */
    public abstract void execute();

}
