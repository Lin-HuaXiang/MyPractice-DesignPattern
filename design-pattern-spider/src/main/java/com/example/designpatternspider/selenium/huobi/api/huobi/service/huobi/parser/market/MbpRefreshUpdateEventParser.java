package com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.market;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MbpRefreshUpdateEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.parser.HuobiModelParser;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.utils.DataUtils;

public class MbpRefreshUpdateEventParser implements HuobiModelParser<MbpRefreshUpdateEvent> {

  @Override
  public MbpRefreshUpdateEvent parse(JSONObject json) {
    String dataKey = DataUtils.getDataKey(json);

    JSONObject data = json.getJSONObject(dataKey);

    return MbpRefreshUpdateEvent.builder()
        .topic(json.getString("ch"))
        .ts(json.getLong("ts"))
        .seqNum(data.getLong("seqNum"))
        .bids(new PriceLevelParser().parseArray(data.getJSONArray("bids")))
        .asks(new PriceLevelParser().parseArray(data.getJSONArray("asks")))
        .build();
  }

  @Override
  public MbpRefreshUpdateEvent parse(JSONArray json) {
    return null;
  }

  @Override
  public List<MbpRefreshUpdateEvent> parseArray(JSONArray jsonArray) {
    return null;
  }
}
