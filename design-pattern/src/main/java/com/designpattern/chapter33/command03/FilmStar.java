package com.designpattern.chapter33.command03;

public class FilmStar implements IStar {

    @Override
    public void act(String context) {
        System.out.println("明星演戏：" + context);
    }
}
