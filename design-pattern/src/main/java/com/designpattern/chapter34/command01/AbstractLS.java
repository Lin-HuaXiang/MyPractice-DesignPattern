package com.designpattern.chapter34.command01;

/**
 * 抽象ls命令，表示所有ls命令的父类
 */

public abstract class AbstractLS extends CommandName {

    // 默认参数
    public final String DEFAULT_PARAM = "";

    // 参数a
    public final String A_PARAM = "a";

    // 参数b
    public final String L_PARAM = "l";


}
