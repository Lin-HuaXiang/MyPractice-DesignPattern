package com.example.designpatterncommand.cook.impl;

import com.example.designpatterncommand.cook.ICook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SiChuanCook implements ICook {

    @Override
    public void doCooking() {
        // TODO Auto-generated method stub
        log.info("四川厨师，烹饪川菜，中国最有特⾊色的菜系，也是⺠民间最⼤大菜系。");
    }

}
