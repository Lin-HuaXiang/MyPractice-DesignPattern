package com.designpattern.chapter10_TemplateMethodPattern.command_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClient {

    public static void main(String[] args) throws IOException {
        System.out.println("----- H1型号悍马 -------");
        System.out.println("H1型号的悍马是否需要喇叭声响？ 0-不需要 1-需要");
        String type = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        HummerH1Model3 h1 = new HummerH1Model3();
        if ("0".equals(type)) {
            h1.setAlarmFlag(false);
        }
        h1.run();
        System.out.println("/n ----H2型号悍马------");
        HummerH2Model3 h2 = new HummerH2Model3();
        h2.run();
    }
}
