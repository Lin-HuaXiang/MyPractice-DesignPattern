package com.designpattern.chapter33.command04;

public class Singer extends AbsStar {

    // 歌星的默认活动是唱歌
    public Singer() {
        super(new Sing());
    }

    // 也可以重新设置一个新的职业
    public Singer(AbsAction action) {
        super(action);
    }

    // 细化歌星的职责
    public void doJob() {
        System.out.println("歌星的工作");
        super.doJob();
    }
}
