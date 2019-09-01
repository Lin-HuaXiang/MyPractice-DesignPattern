package com.designpattern.chapter34.command01;

/**
 * 文件管理类，模拟unix命令在window下执行
 */
public class FileManager {

    // ls命令
    public static String ls(String path) {
        return "file1\nfile2\nfile3\nfile4";
    }

    // ls -l 命令
    public static String ls_l(String path) {
        String str = "drw-rw-rw root system 1024 2019-08-20 10:23 file1\n";
        str += "drw-rw-rw root system 1024 2019-08-20 10:23 file2\n";
        str += "drw-rw-rw root system 1024 2019-08-20 10:23 file3\n";
        str += "drw-rw-rw root system 1024 2019-08-20 10:23 file4\n";
        return str;
    }

    // ls -a
    public static String ls_a(String path) {
        String str = ".\n..\nfile1\nfile2\nfile3";
        return str;
    }
}
