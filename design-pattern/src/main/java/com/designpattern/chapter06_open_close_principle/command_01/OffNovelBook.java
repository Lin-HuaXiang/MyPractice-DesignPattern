package com.designpattern.chapter06_open_close_principle.command_01;

public class OffNovelBook extends NovelBook {

    public OffNovelBook(String _name, int _price, String _author) {
        super(_name, _price, _author);
    }

    @Override
    public int getPrice() {
        // 先获取原价
        int selfPrice = super.getPrice();
        // 再做打折处理
        int offPrice = 0;
        if (selfPrice > 4000) {
            offPrice = selfPrice * 90 / 100;
        } else {
            offPrice = selfPrice * 80 / 100;
        }
        return offPrice;
    }

}

