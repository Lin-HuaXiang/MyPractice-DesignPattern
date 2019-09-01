package com.designpattern.chapter34.command01;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    public abstract String execute(CommandVO vo);

    protected final List<? extends CommandName> buildChain(Class<? extends CommandName> abstractClass) {
        // 取出所有的命令名下的子类
        List<Class> classes = ClassUtils.getSonClass(abstractClass);
        // 存放命令的实例，并建立链表关系
        List<CommandName> commandNameList = new ArrayList<>();
        for (Class clazz : classes) {
            CommandName commandName = null;
            try {
                // 产生实例
                commandName = (CommandName) Class.forName(clazz.getName()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 建立链表
            if (commandNameList.size() > 0) {
                // 相当于，如果存在实例，上面一个实例会成为下一个实例的next
                commandNameList.get(commandNameList.size() - 1).setNext(commandName);
            }
            commandNameList.add(commandName);
        }
        return commandNameList;
    }
}
