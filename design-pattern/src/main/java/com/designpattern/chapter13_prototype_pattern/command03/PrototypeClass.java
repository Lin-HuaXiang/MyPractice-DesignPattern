package com.designpattern.chapter13_prototype_pattern.command03;

/**
 * @author linhuaxiang
 * @date 2019/7/7
 */
public class PrototypeClass implements Cloneable {

    /**
     * override superclass object method
     */
    @Override
    protected PrototypeClass clone() throws CloneNotSupportedException {
        PrototypeClass prototypeClass = null;
        try {
            prototypeClass = (PrototypeClass) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototypeClass;
    }
}
