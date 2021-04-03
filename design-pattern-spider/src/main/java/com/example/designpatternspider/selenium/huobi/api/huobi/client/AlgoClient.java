package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.algo.CancelAlgoOrderRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.algo.CreateAlgoOrderRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.algo.GetHistoryAlgoOrdersRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.algo.GetOpenAlgoOrdersRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.algo.AlgoOrder;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.algo.CancelAlgoOrderResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.algo.CreateAlgoOrderResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.algo.GetHistoryAlgoOrdersResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.algo.GetOpenAlgoOrdersResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiAlgoService;

public interface AlgoClient {

  CreateAlgoOrderResult createAlgoOrder(CreateAlgoOrderRequest request);

  CancelAlgoOrderResult cancelAlgoOrder(CancelAlgoOrderRequest request);

  GetOpenAlgoOrdersResult getOpenAlgoOrders(GetOpenAlgoOrdersRequest request);

  GetHistoryAlgoOrdersResult getHistoryAlgoOrders(GetHistoryAlgoOrdersRequest request);

  AlgoOrder getAlgoOrdersSpecific(String clientOrderId);


  static AlgoClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiAlgoService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
