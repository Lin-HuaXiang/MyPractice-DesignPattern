package com.designpattern.chapter13_prototype_pattern.command06;

import java.util.ArrayList;

/**
 * @author linhuaxiang
 * @date   2019/7/8
 */
public class ThingSix implements Cloneable {

    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected ThingSix clone() {
        ThingSix thing = null;
        try {
            thing = (ThingSix) super.clone();
            thing.arrayList = (ArrayList<String>) this.arrayList.clone();
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
