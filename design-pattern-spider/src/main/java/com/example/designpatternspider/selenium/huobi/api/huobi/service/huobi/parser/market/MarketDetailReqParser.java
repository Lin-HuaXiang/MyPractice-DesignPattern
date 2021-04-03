package com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.market;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDetail;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDetailEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDetailReq;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.HuobiModelParser;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.utils.DataUtils;

public class MarketDetailReqParser implements HuobiModelParser<MarketDetailReq> {

  @Override
  public MarketDetailReq parse(JSONObject json) {
    String dataKey = DataUtils.getDataKey(json);

    JSONObject data = json.getJSONObject(dataKey);
    MarketDetail marketDetail = new MarketDetailParser().parse(data);
    return MarketDetailReq.builder()
        .ch(json.getString("rep"))
        .ts(json.getLong("ts"))
        .detail(marketDetail)
        .build();
  }

  @Override
  public MarketDetailReq parse(JSONArray json) {
    return null;
  }

  @Override
  public List<MarketDetailReq> parseArray(JSONArray jsonArray) {
    return null;
  }
}
