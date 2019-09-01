package com.designpattern.chapter33.command01;

public class Agent implements IStar {

    // 定义是谁经纪人
    private IStar star;

    // 构造函数传递明星
    public Agent(IStar star) {
        this.star = star;
    }

    // 经纪人是不会签字的，签了字歌迷也不认
    public void sign() {
        star.sign();
    }

}
