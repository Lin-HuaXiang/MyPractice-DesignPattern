package com.designpattern.chapter16_chain_of_responsibility_pattern.command02;

import com.designpattern.chapter16_chain_of_responsibility_pattern.command01.IWomen;

public class Father extends Handler {

    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }

    /**
     * The unmarried daughter came to consult her father
     * @param women
     */
    @Override
    protected void response(IWomen women) {
        System.out.println("the daughter's request" + women.getRequest());
        System.out.println("father's answer is yes");
    }
}
