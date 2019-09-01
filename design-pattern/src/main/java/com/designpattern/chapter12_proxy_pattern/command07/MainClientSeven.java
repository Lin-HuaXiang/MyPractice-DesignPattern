package com.designpattern.chapter12_proxy_pattern.command07;

import com.designpattern.chapter12_proxy_pattern.command01.GamePlayerOne;
import com.designpattern.chapter12_proxy_pattern.command01.IGamePlayerOne;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author linhuaxiang
 * @date   2019/7/6
 */
public class MainClientSeven {

    public static void main(String[] args) {
        // define an obsessive player
        IGamePlayerOne player = new GamePlayerOne("zhang san");
        // define a handler
        InvocationHandler handler = new GamePlayerInvocationHandlerSeven(player);
        // begin to play game, record start game timestamp
        System.out.println("start time: 2019/7/6 16:17:25");
        // acquire class loader from class
        ClassLoader classLoader = player.getClass().getClassLoader();
        // dynamically generate a proxy
        IGamePlayerOne proxy = (IGamePlayerOne) Proxy.newProxyInstance(classLoader, new Class[]{IGamePlayerOne.class}, handler);
        // sign on
        proxy.login("zhang san", "password");
        // begin to kill boss
        proxy.killBoss();
        // upgrade
        proxy.upgrade();
        // record end game timestamp
        System.out.println("end time: 2019/7/6 16:23:16");
    }
}
