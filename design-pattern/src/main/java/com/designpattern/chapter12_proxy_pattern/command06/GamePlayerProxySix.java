package com.designpattern.chapter12_proxy_pattern.command06;

import com.designpattern.chapter12_proxy_pattern.command01.IGamePlayerOne;
import com.designpattern.chapter12_proxy_pattern.command05.IGamePlayerFive;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class GamePlayerProxySix implements IGamePlayerOne, IProxySix {


    private IGamePlayerOne gamePlayerOne = null;

    /**
     * pass through the constructor to indicate what to represent
     * @param gamePlayerOne
     */
    public GamePlayerProxySix(IGamePlayerOne gamePlayerOne) {
        this.gamePlayerOne = gamePlayerOne;
    }

    /**
     * the proxy of kill boss
     */
    @Override
    public void killBoss() {
        this.gamePlayerOne.killBoss();
    }

    /**
     * the proxy of login
     * @param user
     * @param password
     */
    @Override
    public void login(String user, String password) {
        this.gamePlayerOne.login(user, password);
    }

    /**
     * the proxy of upgrade
     */
    @Override
    public void upgrade() {
        this.gamePlayerOne.upgrade();
        this.count();
    }

    /**
     * calculate cost
     */
    @Override
    public void count() {
        System.out.println("the total upgrade cost is 150");
    }
}
