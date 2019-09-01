package com.designpattern.chapter16_chain_of_responsibility_pattern.command02;

import com.designpattern.chapter16_chain_of_responsibility_pattern.command01.IWomen;

public class Women implements IWomen {

    private int type = 0;

    private String request = "";

    public Women(int type, String request) {
        this.type = type;
        switch (this.type) {
            case 1:
                this.request = "女儿的请求是：" + request;
                break;
            case 2:
                this.request = "妻子的请求是：" + request;
                break;
            case 3:
                this.request = "母亲的请求是：" + request;
                break;
        }
        this.request = request;
    }

    @Override
    public String getRequest() {
        return this.request;
    }

    @Override
    public int getType() {
        return this.type;
    }
}
