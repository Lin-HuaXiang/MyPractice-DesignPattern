package com.designpattern.chapter33.command04;

public class Client {

    public static void main(String[] args) {
        // 声明一个电影明星
        AbsStar zhangSan = new FilmStar();
        // 声明一个歌星
        AbsStar liSi = new Singer();
        // 展示一下各个明星的主要工作
        zhangSan.doJob();
        liSi.doJob();
        // 当然，也有部分明星不务正业，比如歌星演戏
        liSi = new Singer(new ActFilm());
        liSi.doJob();
    }
}
