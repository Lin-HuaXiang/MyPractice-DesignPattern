package com.designpattern.chapter16_chain_of_responsibility_pattern.command02;

import com.designpattern.chapter16_chain_of_responsibility_pattern.command01.IWomen;

public class Son extends Handler {

    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("母亲的请示是：" + women.getRequest());
        System.out.println("儿子的答复是：同意");
    }
}
