package com.designpattern.chapter13_prototype_pattern.command07;

import java.util.ArrayList;

/**
 * @author linhuaxiang
 * @date   2019/7/9
 */
public class ThingSeven implements Cloneable {

    /**
     * define a private variable, do not add 'final' to member variable of a class
     */
//    private final ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ThingSeven thing = null;
        try {
            thing = (ThingSeven) super.clone();
            thing.arrayList = (ArrayList<String>) this.arrayList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return super.clone();
    }

}