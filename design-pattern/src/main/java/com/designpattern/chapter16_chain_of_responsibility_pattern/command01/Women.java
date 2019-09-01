package com.designpattern.chapter16_chain_of_responsibility_pattern.command01;

/**
 * @author linhuaxiang
 * @date   2019/7/12
 */
public class Women implements IWomen {

    /**
     * describe a woman's personal status by a type of int parameter
     * 1 -- maiden
     * 2 -- marry
     * 3 -- husband die
     * @return
     */

    private int type = 0;

    /**
     * women's request
     */
    private String request = "";

    public Women(int type, String request) {
        this.type = type;
        this.request = request;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
