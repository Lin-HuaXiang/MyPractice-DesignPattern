package com.designpattern.chapter16_chain_of_responsibility_pattern.command02;

import com.designpattern.chapter16_chain_of_responsibility_pattern.command01.IWomen;

public class Husband extends Handler {

    public Husband() {
        super(Handler.HUSBAND_LEVEL_REQUEST);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("妻子的请示是：" + women.getRequest());
        System.out.println("丈夫的答复是：同意");
    }
}
