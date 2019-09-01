package com.designpattern.chapter34.command01;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要的命令配置文件
 * 用枚举限定可以执行的命令
 */
public enum CommandEnum {

    ls("com.designpattern.chapter34.command01.LSCommand"),
    ;

    private String value = "";

    // 定义构造函数，目的是Data(value)类型的相匹配
    private CommandEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    // 返回所有的enum对象
    public static List<String> getNames() {
        CommandEnum[] commandEnums = CommandEnum.values();
        List<String> names = new ArrayList<>();
        for (CommandEnum e : commandEnums) {
            names.add(e.name());
        }
        return names;
    }
}
