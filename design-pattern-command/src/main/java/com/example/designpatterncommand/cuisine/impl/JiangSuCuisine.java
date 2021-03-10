package com.example.designpatterncommand.cuisine.impl;

import com.example.designpatterncommand.cook.ICook;
import com.example.designpatterncommand.cuisine.ICuisine;

public class JiangSuCuisine implements ICuisine {

    private ICook cook;

    public JiangSuCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
    
}
