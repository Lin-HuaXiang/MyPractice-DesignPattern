package com.designpattern.chapter12_proxy_pattern.command05;

public interface IGamePlayerFive {

    // log in the game
    void login(String user, String password);

    // kill boss, this is the main feature of online games
    void killBoss();

    // upgrade
    void upgrade();

    // everyone can find their own proxy
    IGamePlayerFive getProxy();
}
