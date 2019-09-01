package com.designpattern.chapter33.command03;

public class Standin implements IStar {

    private IActor actor;

    public Standin(IActor actor) {
        this.actor = actor;
    }

    @Override
    public void act(String context) {
        actor.playact(context);
    }
}
