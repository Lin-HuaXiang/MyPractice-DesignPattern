package com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.subuser;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserState;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.HuobiModelParser;

public class SubUserStateParser implements HuobiModelParser<SubUserState> {

  @Override
  public SubUserState parse(JSONObject json) {
    return json.toJavaObject(SubUserState.class);
  }

  @Override
  public SubUserState parse(JSONArray json) {
    return null;
  }

  @Override
  public List<SubUserState> parseArray(JSONArray jsonArray) {
    if (jsonArray == null || jsonArray.isEmpty()) {
      return new ArrayList<>();
    }
    return jsonArray.toJavaList(SubUserState.class);
  }
}