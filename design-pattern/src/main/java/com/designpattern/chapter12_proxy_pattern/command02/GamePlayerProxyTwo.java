package com.designpattern.chapter12_proxy_pattern.command02;

import com.designpattern.chapter12_proxy_pattern.command01.IGamePlayerOne;

public class GamePlayerProxyTwo implements IGamePlayerOne {

    private IGamePlayerOne gamePlayerOne = null;

    // pass in the constractor who you want to represent
    public GamePlayerProxyTwo(IGamePlayerOne _gamePlayerOne) {
        this.gamePlayerOne = _gamePlayerOne;
    }

    // agent login
    @Override
    public void login(String user, String password) {
        this.gamePlayerOne.login(user, password);
    }

    // agent kill boss
    @Override
    public void killBoss() {
        this.gamePlayerOne.killBoss();
    }

    // agent upgrade
    @Override
    public void upgrade() {
        this.gamePlayerOne.upgrade();
    }
}
