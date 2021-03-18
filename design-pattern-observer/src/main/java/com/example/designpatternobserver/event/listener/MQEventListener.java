package com.example.designpatternobserver.event.listener;

import com.example.designpatternobserver.LotteryResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MQEventListener implements EventListener {

    @Override
    public void doEvent(LotteryResult result) {
        log.info("记录用户 {} 摇号结果（MQ）：{}", result.getUId(), result.getMsg());

    }

}
