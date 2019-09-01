package com.designpattern.chapter16_chain_of_responsibility_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public interface IHandler {

    /**
     * A women asks to shop, and you handle the request
     * @param women
     */
    void handleMessage(IWomen women);
    
}
