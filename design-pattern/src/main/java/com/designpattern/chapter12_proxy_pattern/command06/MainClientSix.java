package com.designpattern.chapter12_proxy_pattern.command06;

import com.designpattern.chapter12_proxy_pattern.command01.GamePlayerOne;
import com.designpattern.chapter12_proxy_pattern.command01.IGamePlayerOne;
import com.designpattern.chapter12_proxy_pattern.command05.GamePlayerFive;
import com.designpattern.chapter12_proxy_pattern.command05.GamePlayerProxyFive;
import com.designpattern.chapter12_proxy_pattern.command05.IGamePlayerFive;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class MainClientSix {


    /**
     * direct assess to real roles
     * @param args
     */
    public static void main1(String[] args) {
        // define a game character
        IGamePlayerFive player = new GamePlayerFive("zhang san");
        // start game, record timestamp
        System.out.println("start time: 2019-7-6 15:05:05");
        player.login("zhang san", "password");
        // begin to kill boss
        player.killBoss();
        // upgrade
        player.upgrade();
    }

    /**
     * direct access to proxy class
     * @param args
     */
    public static void main2(String[] args) {
        // define a game character
        IGamePlayerFive playerFive = new GamePlayerFive("zhang san");
        // then define a game proxy
        IGamePlayerFive proxy = new GamePlayerProxyFive(playerFive);
        // begin to play game, record start game timestamp
        System.out.println("start time：2019-7-6 15:13:02");
        proxy.login("zhangsan", "password");
        // begin to kill boss
        proxy.killBoss();
        // upgrade
        proxy.upgrade();
        // record end game timestamp
        System.out.println("end time: 2019-7-6 15:14:27");
    }

    /**
     * enforce the user of proxy
     * @param args
     */
    public static void main(String[] args) {
        // define a game character
        IGamePlayerOne gamePlayer = new GamePlayerOne("zhang san");
        // acquire the specified proxy, enforce the use of proxy provide in read roles
        IGamePlayerOne proxy = new GamePlayerProxySix(gamePlayer);
        // begin to play game, record start game timestamp
        System.out.println("start time: 2019/7/6 15:20:47");
        proxy.login("zhang san", "password");
        // begin to kill boss
        proxy.killBoss();
        // upgrade
        proxy.upgrade();
        // record end game timestamp
        System.out.println("end time: 2019/7/6 15:22:43");
    }
}
