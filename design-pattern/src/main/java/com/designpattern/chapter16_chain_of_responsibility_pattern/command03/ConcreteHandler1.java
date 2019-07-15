package com.designpattern.chapter16_chain_of_responsibility_pattern.command03;

public class ConcreteHandler1 extends Handler {

    @Override
    public void setNext(Handler handler) {
        super.setNext(handler);
    }

    @Override
    protected Level getHandlerLevel() {
        return null;
    }

    @Override
    protected Response echo(Request request) {
        return null;
    }
}
