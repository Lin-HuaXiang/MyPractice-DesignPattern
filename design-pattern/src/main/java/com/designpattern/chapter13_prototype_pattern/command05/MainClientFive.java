package com.designpattern.chapter13_prototype_pattern.command05;

/**
 * shallow clone
 */
public class MainClientFive {

    public static void main(String[] args) {
        // define an object
        ThingFive thing = new ThingFive();
        // set up a variable
        thing.setValue("zhang san");
        // copy an object
        ThingFive cloneThine = thing.clone();
        cloneThine.setValue("li si");
        System.out.println(thing.getValue());
    }
}
