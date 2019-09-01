package com.designpattern.chapter33.command04;

public class FilmStar extends AbsStar {

    // 默认的电影明星的主要工作是拍电影
    public FilmStar() {
        super(new ActFilm());
    }

    public FilmStar(AbsAction action) {
        super(action);
    }

    @Override
    public void doJob() {
        System.out.println("影星的工作");
        super.doJob();
    }
}
