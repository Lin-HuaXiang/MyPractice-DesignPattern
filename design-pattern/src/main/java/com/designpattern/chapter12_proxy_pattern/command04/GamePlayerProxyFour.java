package com.designpattern.chapter12_proxy_pattern.command04;

import com.designpattern.chapter12_proxy_pattern.command01.IGamePlayerOne;

public class GamePlayerProxyFour implements IGamePlayerOne {

    private IGamePlayerOne gamePlayerOne = null;

    public GamePlayerProxyFour(String name) {
        try {
            gamePlayerOne = new GamePlayerFour(this, name);
        } catch (Exception e) {
            // exception handling
        }
    }


    @Override
    public void login(String user, String password) {
        this.gamePlayerOne.login(user, password);
    }

    // proxy kill boss
    @Override
    public void killBoss() {
        this.gamePlayerOne.killBoss();
    }

    // proxy upgrade
    @Override
    public void upgrade() {
        this.gamePlayerOne.upgrade();
    }
}
