package com.example.designpatternobserver.event.listener;

import com.example.designpatternobserver.LotteryResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageEventListener implements EventListener {

    @Override
    public void doEvent(LotteryResult result) {
        log.info("给用户 {} 发送短信通知（短信）：{}", result.getUId(), result.getMsg());
    }

}
