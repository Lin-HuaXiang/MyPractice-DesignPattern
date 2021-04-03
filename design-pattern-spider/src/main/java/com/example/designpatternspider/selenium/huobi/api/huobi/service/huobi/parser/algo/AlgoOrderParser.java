package com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.algo;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.designpatternspider.selenium.huobi.api.huobi.model.algo.AlgoOrder;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.HuobiModelParser;

public class AlgoOrderParser implements HuobiModelParser<AlgoOrder> {

  @Override
  public AlgoOrder parse(JSONObject json) {
    return json.toJavaObject(AlgoOrder.class);
  }

  @Override
  public AlgoOrder parse(JSONArray json) {
    return null;
  }

  @Override
  public List<AlgoOrder> parseArray(JSONArray jsonArray) {
    return jsonArray.toJavaList(AlgoOrder.class);
  }
}
