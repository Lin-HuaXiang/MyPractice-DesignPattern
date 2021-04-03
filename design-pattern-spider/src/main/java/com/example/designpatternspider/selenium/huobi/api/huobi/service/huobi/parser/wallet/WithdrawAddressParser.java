package com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.wallet;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.designpatternspider.selenium.huobi.api.huobi.model.wallet.WithdrawAddress;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.HuobiModelParser;

public class WithdrawAddressParser implements HuobiModelParser<WithdrawAddress> {

  @Override
  public WithdrawAddress parse(JSONObject json) {
    return null;
  }

  @Override
  public WithdrawAddress parse(JSONArray json) {
    return null;
  }

  @Override
  public List<WithdrawAddress> parseArray(JSONArray jsonArray) {
    return jsonArray.toJavaList(WithdrawAddress.class);
  }
}