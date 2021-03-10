package com.example.designpatterncommand.cook.impl;

import com.example.designpatterncommand.cook.ICook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JiangSuCook implements ICook { 

    public void doCooking() {
        log.info("江苏厨师，烹饪苏菜，宫廷第二大菜系，古今国宴上最受⼈人欢迎的菜系。");
    }
}
