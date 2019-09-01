package com.designpattern.chapter13_prototype_pattern.command06;

import com.designpattern.chapter13_prototype_pattern.command05.ThingFive;
import com.designpattern.chapter13_prototype_pattern.command06.ThingSix;

/**
 * 
 * @author linhuaxiang
 * @date   2019/7/8
 */
public class MainClientSix {

    public static void main(String[] args) {
        // define an object
        ThingSix thing = new ThingSix();
        // set up a variable
        thing.setValue("zhang san");
        // copy an object
        ThingSix cloneThine = thing.clone();
        cloneThine.setValue("li si");
        System.out.println(thing.getValue());
    }
}
