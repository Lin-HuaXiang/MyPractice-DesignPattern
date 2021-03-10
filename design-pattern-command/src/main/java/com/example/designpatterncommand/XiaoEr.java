package com.example.designpatterncommand;

import java.util.ArrayList;
import java.util.List;

import com.example.designpatterncommand.cuisine.ICuisine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XiaoEr {

    private List<ICuisine> cuisineList = new ArrayList<>();

    public void order(ICuisine cuisine) {
        cuisineList.add(cuisine);
    }
    
    public synchronized void placeOrder() {
        for (ICuisine cuisine : cuisineList) {
            cuisine.cook();
        }
        cuisineList.clear();
    }

}
