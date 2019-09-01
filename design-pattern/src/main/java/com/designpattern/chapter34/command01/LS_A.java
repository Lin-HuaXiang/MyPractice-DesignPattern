package com.designpattern.chapter34.command01;

/**
 * ls -a命令类
 */
public class LS_A extends AbstractLS {

    // ls -a命令
    protected String echo(CommandVO vo) {
        return FileManager.ls_a(vo.formatData());
    }

    protected String getOperatorParam() {
        return super.A_PARAM;
    }
}
