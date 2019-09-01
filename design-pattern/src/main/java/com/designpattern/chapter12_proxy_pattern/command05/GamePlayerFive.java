package com.designpattern.chapter12_proxy_pattern.command05;

public class GamePlayerFive implements IGamePlayerFive {

    private String name = "";

    // who is my proxy
    private IGamePlayerFive proxy = null;

    public GamePlayerFive(String name) {
        this.name = name;
    }


    // find own proxy
    @Override
    public IGamePlayerFive getProxy() {
        this.proxy = new GamePlayerProxyFive(this);
        return this.proxy;
    }

    // kill monster, most expect to kill the elite monster
    @Override
    public void killBoss() {
        if (this.isProxy()) {
            System.out.println(this.name + "killing boss");
        } else {
            System.out.println("access using the specified proxy class");
        }
    }

    @Override
    public void login(String use, String password) {
        if (this.isProxy()) {
            System.out.println("loginname: " + use + ", trueName: "  + this.name + "login success");
        } else {
            System.out.println("access using the specified proxy class");
        }
    }

    // Upgrade, there are many ways to upgrade, spend money to buy or do tasks
    @Override
    public void upgrade() {
        if (this.isProxy()) {
            System.out.println(this.name + "upgrade again ");
        } else {
            System.out.println("access using the specified proxy class");
        }
    }

    // verify whether access is for proxy
    private boolean isProxy() {
        if (this.proxy == null) {
            return false;
        } else {
            return true;
        }
    }

}
