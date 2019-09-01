package com.designpattern.chapter12_proxy_pattern.command09;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class BeforeAdviceNine implements IAdviceNine {

    @Override
    public void exec() {
        System.out.println("I was before advice. I was executed.");
    }
}
