package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.CandlestickRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.MarketDepthRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.MarketDetailMergedRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.MarketDetailRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.MarketHistoryTradeRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.MarketTradeRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.ReqCandlestickRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.ReqMarketDepthRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.ReqMarketDetailRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.ReqMarketTradeRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.SubCandlestickRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.SubMarketBBORequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.SubMarketDepthRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.SubMarketDetailRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.SubMarketTradeRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.SubMbpIncrementalUpdateRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market.SubMbpRefreshUpdateRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.Candlestick;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.CandlestickEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.CandlestickReq;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketBBOEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDepth;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDepthEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDepthReq;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDetail;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDetailEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDetailMerged;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketDetailReq;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketTicker;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketTrade;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketTradeEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MarketTradeReq;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MbpIncrementalUpdateEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.market.MbpRefreshUpdateEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiMarketService;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.connection.HuobiWebSocketConnection;
import com.example.designpatternspider.selenium.huobi.api.huobi.utils.ResponseCallback;
import com.example.designpatternspider.selenium.huobi.api.huobi.utils.WebSocketConnection;

public interface MarketClient {

  List<Candlestick> getCandlestick(CandlestickRequest request);

  MarketDetailMerged getMarketDetailMerged(MarketDetailMergedRequest request);

  MarketDetail getMarketDetail(MarketDetailRequest request);

  List<MarketTicker> getTickers();

  MarketDepth getMarketDepth(MarketDepthRequest request);

  List<MarketTrade> getMarketTrade(MarketTradeRequest request);

  List<MarketTrade> getMarketHistoryTrade(MarketHistoryTradeRequest request);

  void subCandlestick(SubCandlestickRequest request, ResponseCallback<CandlestickEvent> callback);

  void subMarketDetail(SubMarketDetailRequest request, ResponseCallback<MarketDetailEvent> callback);

  void subMarketDepth(SubMarketDepthRequest request, ResponseCallback<MarketDepthEvent> callback);

  void subMarketTrade(SubMarketTradeRequest request, ResponseCallback<MarketTradeEvent> callback);

  void subMarketBBO(SubMarketBBORequest request, ResponseCallback<MarketBBOEvent> callback);

  void subMbpRefreshUpdate(SubMbpRefreshUpdateRequest request, ResponseCallback<MbpRefreshUpdateEvent> callback);

  WebSocketConnection subMbpIncrementalUpdate(SubMbpIncrementalUpdateRequest request, ResponseCallback<MbpIncrementalUpdateEvent> callback);

  WebSocketConnection reqMbpIncrementalUpdate(SubMbpIncrementalUpdateRequest request, WebSocketConnection connection);

  void reqCandlestick(ReqCandlestickRequest request, ResponseCallback<CandlestickReq> callback);

  void reqMarketDepth(ReqMarketDepthRequest request, ResponseCallback<MarketDepthReq> callback);

  void reqMarketTrade(ReqMarketTradeRequest request, ResponseCallback<MarketTradeReq> callback);

  void reqMarketDetail(ReqMarketDetailRequest request, ResponseCallback<MarketDetailReq> callback);

  static MarketClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiMarketService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }


}
