package com.designpattern.chapter12_proxy_pattern.command02;

import com.designpattern.chapter12_proxy_pattern.command01.GamePlayerOne;
import com.designpattern.chapter12_proxy_pattern.command01.IGamePlayerOne;

public class MainClientTwo {

    public static void main(String args) {
        // define an obsessive palyer
        IGamePlayerOne player = new GamePlayerOne("zhangsan");
        // then define a surrogate
        IGamePlayerOne proxy = new GamePlayerProxyTwo(player);
        // start playing games, write down the timestamp
        System.out.println("start time : 2019-06-27 21:56:00");
        proxy.login("zhangsan", "password");
        // start kill boss
        proxy.killBoss();
        // upgrade
        proxy.upgrade();
        // write down the end of game time
        System.out.println("end time : 2019-06-27 22:01:00");
    }

}
