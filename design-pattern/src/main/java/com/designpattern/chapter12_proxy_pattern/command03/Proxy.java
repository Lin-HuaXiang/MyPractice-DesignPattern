package com.designpattern.chapter12_proxy_pattern.command03;

public class Proxy implements Subject {

    // which implementation class to proxy
    private Subject subject = null;

    // default agent
    public Proxy() {
        this.subject = new Proxy();
    }

    // pass the proxy through the constructor
    public Proxy(Object... objects) {

    }

    //
    public Proxy(Subject _subject) {
        this.subject = _subject;
    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    // pretreatment
    private void before() {
        // do something
    }

    // afterwards
    private void after() {
        // do something
    }

}
