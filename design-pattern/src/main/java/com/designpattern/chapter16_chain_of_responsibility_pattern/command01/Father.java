package com.designpattern.chapter16_chain_of_responsibility_pattern.command01;

public class Father implements  IHandler {

    /**
     * The unmarried daughter came to consult her father
     * @param women
     */
    @Override
    public void handleMessage(IWomen women) {
        System.out.println("the daughter's request" + women.getRequest());
        System.out.println("father's answer is yes");
    }
}
