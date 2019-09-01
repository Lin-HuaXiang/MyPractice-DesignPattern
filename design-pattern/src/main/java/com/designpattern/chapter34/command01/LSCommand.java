package com.designpattern.chapter34.command01;

public class LSCommand extends Command {

    public String execute(CommandVO vo) {
        // 返回链表的首节点
        CommandName firstNode = super.buildChain(AbstractLS.class).get(0);
        // 首节点处理命令，首节点处理命令模式
        // 系统由这里开始启动
        return firstNode.handleMessage(vo);
    }
}
