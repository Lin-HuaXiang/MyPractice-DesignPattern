package com.designpattern.chapter13_prototype_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class AdvTemplate {

    /**
     * advertising information name
     */
    private String advSubject = "National Day credit card lottery of XX bank";

    /**
     * advertising information context
     */
    private String advContext = "National Day lottery announcement: Just swipe you credit card and you get a million dollars";

    /**
     * acquire advertising information name
     * @return
     */
    public String getAdvSubject() {
        return this.advSubject;
    }

    /**
     * acquire advertising information context
     * @return
     */
    public String getAdvContext() {
        return this.advContext;
    }

}
