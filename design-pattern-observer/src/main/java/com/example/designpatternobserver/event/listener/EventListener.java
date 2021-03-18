package com.example.designpatternobserver.event.listener;

import com.example.designpatternobserver.LotteryResult;

public interface EventListener {

    void doEvent(LotteryResult result);
    
}
