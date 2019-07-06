package com.designpattern.chapter12_proxy_pattern.command04;

import com.designpattern.chapter12_proxy_pattern.command01.IGamePlayerOne;

public class MainClientFour {

    public static void main(String[] args) {
        // define a substitute
        IGamePlayerOne proxy = new GamePlayerProxyFour("张三");
        // start playing games, record timestamp
        System.out.println("begin time: 2019-7-6 10:29:45");
        proxy.login("zhangSan", "password");
        // start kill boss
        proxy.killBoss();
        // upgrade
        proxy.upgrade();
        // record end game time
        System.out.println("end time: 2019-7-6 10:31:48");

    }
}
