package com.designpattern.chapter33.command04;

public abstract class AbsStar {

    // 一个明星参加那些活动
    protected final AbsAction action;

    // 通过构造函数传递具体活动
    public AbsStar(AbsAction action) {
        this.action = action;
    }

    // 每个明星都有自己的主要工作
    public void doJob() {
        action.desc();
    }
}
