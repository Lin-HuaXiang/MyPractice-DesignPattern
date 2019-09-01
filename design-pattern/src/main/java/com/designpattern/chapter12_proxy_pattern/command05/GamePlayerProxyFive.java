package com.designpattern.chapter12_proxy_pattern.command05;

public class GamePlayerProxyFive implements IGamePlayerFive {

    private IGamePlayerFive gamePlayerFive = null;

    // the constructor passes the username

    public GamePlayerProxyFive(IGamePlayerFive gamePlayerFive) {
        this.gamePlayerFive = gamePlayerFive;
    }

    // proxy kill boss
    @Override
    public void killBoss() {
        this.gamePlayerFive.killBoss();
    }

    // proxy login
    public void login(String user, String password) {
        this.gamePlayerFive.login(user, password);
    }

    // proxy upgrade
    public void upgrade() {
        this.gamePlayerFive.upgrade();
    }

    // The proxy of the proxy has not yet, is their own
    @Override
    public IGamePlayerFive getProxy() {
        return this;
    }
}
