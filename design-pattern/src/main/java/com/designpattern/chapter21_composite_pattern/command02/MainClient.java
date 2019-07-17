package com.designpattern.chapter21_composite_pattern.command02;

import java.util.List;

public class MainClient {


    public static String getTreeInfo(Branch root) {
        List<Corp> subordinateList = root.getSubordinate();
        StringBuilder info = new StringBuilder();
        for (Corp s : subordinateList) {
            if (s instanceof Leaf) {
                info.append(s.getInfo()).append("\n");
            } else {
                info.append(s.getInfo()).append("\n").append(getTreeInfo((Branch) s));
            }
        }
        return info.toString();
    }
}
