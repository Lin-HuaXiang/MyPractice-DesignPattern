package com.designpattern.chapter15_command_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/10
 */
public class MainClient {

    public static void main2(String[] args) {
        // First the customer comes to the requirement group to talk about the requirements and modify them.
        System.out.println("--- the customer asked for a new feature ---");
        Group rg = new RequirementGroup();
        // find the requirement group
        rg.find();
        // add a requirement
        rg.add();
        // asked for the change of plan
        rg.plan();
    }

    public static void main(String[] args) {
        // First the customer comes to the page group to talk about the page and modify them
        System.out.println("--- the customer asked for delete a page");
        Group pg = new PageGroup();
        // find the page group
        pg.find();
        // delete a page
        pg.delete();
        // asked for the change of plan
        pg.plan();
    }
}
