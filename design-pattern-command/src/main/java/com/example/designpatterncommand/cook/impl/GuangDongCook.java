package com.example.designpatterncommand.cook.impl;

import com.example.designpatterncommand.cook.ICook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuangDongCook implements ICook {

    @Override
    public void doCooking() {
        // TODO Auto-generated method stub
        log.info("广东厨师，烹饪鲁菜，宫廷最⼤菜系，以孔府⻛味为⻰龙头");
    }


    
}
