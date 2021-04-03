package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.etf.ETFSwapListRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.etf.ETFSwapRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.etf.ETFConfig;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.etf.ETFSwapRecord;

public interface ETFClient {

  ETFConfig getConfig(String etfName);

  void etfSwap(ETFSwapRequest request);

  List<ETFSwapRecord> getEtfSwapList(ETFSwapListRequest request);

}
