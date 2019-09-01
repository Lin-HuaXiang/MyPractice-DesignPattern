package com.designpattern.chapter13_prototype_pattern.command05;

import java.util.ArrayList;

/**
 * shallow clone
 * @author linhuaxiang
 * @date   2019/7/8
 */
public class ThingFive implements Cloneable{

    /**
     * define an private variable
     */
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected ThingFive clone() {
        ThingFive thing = null;
        try {
            thing = (ThingFive) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }

    /**
     * getter hashMap
     * @return
     */
    public ArrayList<String> getValue() {
        return arrayList;
    }

    /**
     * setter hashMap
     * @param value
     */
    public void setValue(String value) {
        this.arrayList.add(value);
    }
}
