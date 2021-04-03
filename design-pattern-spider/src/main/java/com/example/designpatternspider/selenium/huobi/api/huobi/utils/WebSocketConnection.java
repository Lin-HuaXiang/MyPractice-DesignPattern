package com.example.designpatternspider.selenium.huobi.api.huobi.utils;

import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ConnectionStateEnum;

public interface WebSocketConnection {

  ConnectionStateEnum getState();

  Long getConnectionId();

  void reConnect();

  void reConnect(int delayInSecond);

  long getLastReceivedTime();

  void send(String str);
}
