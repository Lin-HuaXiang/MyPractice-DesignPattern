package com.example.designpatternobserver;

import com.example.designpatternobserver.event.EventManager;
import com.example.designpatternobserver.event.listener.MQEventListener;
import com.example.designpatternobserver.event.listener.MessageEventListener;

public abstract class LotteryService {
    
    private EventManager eventManager;

    public LotteryService() {
        eventManager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.MESSAGE);
        eventManager.subscribe(EventManager.EventType.MQ, new MQEventListener());
        eventManager.subscribe(EventManager.EventType.MESSAGE, new MessageEventListener());
    }

    public LotteryResult draw(String uId) {
        LotteryResult lotteryResult = doDraw(uId);
        eventManager.notify(EventManager.EventType.MQ, lotteryResult);
        eventManager.notify(EventManager.EventType.MESSAGE, lotteryResult);
        return lotteryResult;
    }

    protected abstract LotteryResult doDraw(String uId);


}
