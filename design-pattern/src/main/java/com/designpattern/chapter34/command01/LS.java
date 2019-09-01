package com.designpattern.chapter34.command01;

/**
 * 普通ls命令类
 */
public class LS extends AbstractLS {

    // 最简单的ls命令
    protected String echo(CommandVO vo) {
        return FileManager.ls(vo.formatData());
    }

    //参数为空
    protected String getOperatorParam() {
        return super.DEFAULT_PARAM;
    }

}
