package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.generic.CurrencyChainsRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.generic.CurrencyChain;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.generic.MarketStatus;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.generic.Symbol;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiGenericService;

public interface GenericClient {

  String getSystemStatus();

  MarketStatus getMarketStatus();

  List<Symbol> getSymbols();

  List<String> getCurrencys();

  List<CurrencyChain> getCurrencyChains(CurrencyChainsRequest request);

  Long getTimestamp();

  static GenericClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiGenericService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
