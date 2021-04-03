package com.example.designpatternspider.selenium.huobi.api.huobi.constant;

import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;

public interface Options {

  String getApiKey();

  String getSecretKey();

  ExchangeEnum getExchange();

  String getRestHost();

  String getWebSocketHost();

  boolean isWebSocketAutoConnect();

}
