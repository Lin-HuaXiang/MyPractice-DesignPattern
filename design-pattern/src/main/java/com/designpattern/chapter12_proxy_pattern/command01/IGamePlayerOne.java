package com.designpattern.chapter12_proxy_pattern.command01;

public interface IGamePlayerOne {
    // 登陆游戏
    void login(String user, String password);

    // 杀怪，网络游戏的特色
    void killBoss();

    // 升级
    void upgrade();
}
