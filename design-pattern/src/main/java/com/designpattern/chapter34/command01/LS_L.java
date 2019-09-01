package com.designpattern.chapter34.command01;

/**
 * ls -l命令类
 */
public class LS_L extends AbstractLS {

    // ls -l命令
    protected String echo(CommandVO vo) {
        return FileManager.ls_l(vo.formatData());
    }

    // l选项
    protected String getOperatorParam() {
        return super.L_PARAM;
    }

}
