package com.designpattern.chapter06_open_close_principle.command_02;

import com.designpattern.chapter06_open_close_principle.command_01.IBook;
import com.designpattern.chapter06_open_close_principle.command_01.OffNovelBook;

import java.text.NumberFormat;
import java.util.ArrayList;

public class BookStore {

    private final static ArrayList<IBook> bookList = new ArrayList<>();
    static {
        bookList.add(new OffNovelBook("天龙八部", 3200, "金庸"));
        bookList.add(new OffNovelBook("巴黎圣母院", 5600, "雨果"));
        bookList.add(new OffNovelBook("悲惨世界", 3500, "雨果"));
        bookList.add(new OffNovelBook("金瓶梅", 431223111, "兰陵笑笑生"));
    }

    public static void main(String[] args) {
        // 格式化
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        // 设置某个数的小数部分中所允许的最大数字位数（测试感觉默认是2，官方是3），会进行四舍五入操作，小数后面为零回舍去，/100因为是能够整除，因此被抹除
        formatter.setMaximumFractionDigits(2);
        // 设置小数部分最小位数默认为0，不足位数补零，
//        formatter.setMinimumFractionDigits(2);
        System.out.println("-----------书店冒出去的书籍记录如下：------------");
        for (IBook book : bookList) {
            System.out.println("书籍名称：" + book.getName() + "\t书籍作者："
                    + book.getAuthor() + "\t书籍价格：" + formatter.format(book.getPrice() / 100.0) + "元");
        }

    }
}
