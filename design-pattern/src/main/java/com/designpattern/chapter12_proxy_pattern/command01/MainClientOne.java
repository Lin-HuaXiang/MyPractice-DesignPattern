package com.designpattern.chapter12_proxy_pattern.command01;

public class MainClientOne {

    public static void main(String[] args) {
        // define an obsessive palyer
        IGamePlayerOne player = new GamePlayerOne("张三");
        // start playing games, write down the timestamp
        System.out.println("开始时间是 ：2019年6月27日21:36:34");
        player.login("zhangsan", "password");
        // begin kill boss
        player.killBoss();
        // upgrade
        player.upgrade();
        // record the game end time
        System.out.println("the end time is 2019-6-27 21:40:40");
    }
}
