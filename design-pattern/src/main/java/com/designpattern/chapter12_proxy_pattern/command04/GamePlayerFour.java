package com.designpattern.chapter12_proxy_pattern.command04;

import com.designpattern.chapter12_proxy_pattern.command01.IGamePlayerOne;

public class GamePlayerFour implements IGamePlayerOne {

    private String name = "";

    // The constructor restricts who can create an object and pass the name at the same time

    public GamePlayerFour(IGamePlayerOne gamePlayerOne, String name) throws Exception {
        if (gamePlayerOne == null) {
            throw new Exception("cannot create real roles");
        } else {
            this.name = name;
        }
    }

    // It is a necessary condition that you must sign on before entering the game
    @Override
    public void login(String user, String password) {
        System.out.println("loginname: " + user + ", trueName: "  + this.name + "login success");
    }

    // kill monster, most expect to kill the elite monster
    @Override
    public void killBoss() {
        System.out.println(this.name + "killing monster");
    }

    // Upgrade, there are many ways to upgrade, spend money to buy or do tasks
    @Override
    public void upgrade() {
        System.out.println(this.name + "upgrade again");
    }
}
