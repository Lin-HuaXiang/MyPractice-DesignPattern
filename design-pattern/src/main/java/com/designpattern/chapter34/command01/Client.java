package com.designpattern.chapter34.command01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws IOException {
        Invoker invoker = new Invoker();
        while (true) {
            // unix下的默认提示符号
            System.out.print("# ");
            // 捕获输出
            String input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            // 输入quit或者exit则退出
            if ("quit".equals(input) || "exit".equals(input)) {
                return;
            }
            System.out.println(invoker.exec(input));
        }
    }
}
