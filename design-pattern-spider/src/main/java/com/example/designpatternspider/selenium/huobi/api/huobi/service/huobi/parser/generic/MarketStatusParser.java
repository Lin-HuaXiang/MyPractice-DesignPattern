package com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.generic;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.designpatternspider.selenium.huobi.api.huobi.model.generic.MarketStatus;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.HuobiModelParser;

public class MarketStatusParser implements HuobiModelParser<MarketStatus> {

  @Override
  public MarketStatus parse(JSONObject json) {
    return json.toJavaObject(MarketStatus.class);
  }

  @Override
  public MarketStatus parse(JSONArray json) {
    return null;
  }

  @Override
  public List<MarketStatus> parseArray(JSONArray jsonArray) {
    return null;
  }
}
