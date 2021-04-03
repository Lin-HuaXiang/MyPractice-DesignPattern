package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.BatchCancelOpenOrdersRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.CreateOrderRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.FeeRateRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.MatchResultRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.OpenOrdersRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.OrderHistoryRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.OrdersRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.ReqOrderListRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.SubOrderUpdateRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.SubOrderUpdateV2Request;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.trade.SubTradeClearingRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.BatchCancelOpenOrdersResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.BatchCancelOrderResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.FeeRate;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.MatchResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.Order;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.OrderDetailReq;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.OrderListReq;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.OrderUpdateEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.OrderUpdateV2Event;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.trade.TradeClearingEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiTradeService;
import com.example.designpatternspider.selenium.huobi.api.huobi.utils.ResponseCallback;

public interface TradeClient {

  Long createOrder(CreateOrderRequest request);

  Long cancelOrder(Long orderId);

  Integer cancelOrder(String clientOrderId);

  BatchCancelOpenOrdersResult batchCancelOpenOrders(BatchCancelOpenOrdersRequest request);

  BatchCancelOrderResult batchCancelOrder(List<Long> ids);

  List<Order> getOpenOrders(OpenOrdersRequest request);

  Order getOrder(Long orderId);

  Order getOrder(String clientOrderId);

  List<Order> getOrders(OrdersRequest request);

  List<Order> getOrdersHistory(OrderHistoryRequest request);

  List<MatchResult> getMatchResult(Long orderId);

  List<MatchResult> getMatchResults(MatchResultRequest request);

  List<FeeRate> getFeeRate(FeeRateRequest request);

  void subOrderUpdateV2(SubOrderUpdateV2Request request, ResponseCallback<OrderUpdateV2Event> callback);

  void subTradeClearing(SubTradeClearingRequest request, ResponseCallback<TradeClearingEvent> callback);

  static TradeClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiTradeService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }

}
