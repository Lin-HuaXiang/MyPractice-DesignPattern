package com.designpattern.chapter16_chain_of_responsibility_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public interface IWomen {

    /**
     * Get personal status
     * @return
     */
    int getType();

    /**
     * Get personal advice. What are you going out for? Go shopping? Dating? Or watch a movie?
     * @return
     */
    String getRequest();


}
