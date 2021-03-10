package com.example.designpatterncommand.cook.impl;

import com.example.designpatterncommand.cook.ICook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShangDongCook implements ICook {

    public void doCooking() {
        log.info("⼭东厨师，烹饪鲁菜，宫廷最⼤菜系，以孔府⻛风味为⻰龙头");
    }

}
