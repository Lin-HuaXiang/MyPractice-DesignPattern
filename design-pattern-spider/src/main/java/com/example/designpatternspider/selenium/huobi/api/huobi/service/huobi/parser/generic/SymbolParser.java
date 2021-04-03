package com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.generic;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.Point;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.generic.Symbol;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.HuobiModelParser;

public class SymbolParser implements HuobiModelParser<Symbol> {

  @Override
  public Symbol parse(JSONObject json) {
    return json.toJavaObject(Symbol.class);
  }

  @Override
  public Symbol parse(JSONArray json) {
    return null;
  }

  @Override
  public List<Symbol> parseArray(JSONArray jsonArray) {
    if (jsonArray == null || jsonArray.size() <= 0) {
      return new ArrayList<>();
    }

    List<Symbol> list = new ArrayList<>();
    for (int i = 0; i < jsonArray.size(); i++) {
      list.add(parse(jsonArray.getJSONObject(i)));
    }

    return list;
  }
}
