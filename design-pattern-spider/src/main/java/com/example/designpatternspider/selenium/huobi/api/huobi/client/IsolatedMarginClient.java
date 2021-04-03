package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.margin.IsolatedMarginAccountRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.margin.IsolatedMarginApplyLoanRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.margin.IsolatedMarginLoanInfoRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.margin.IsolatedMarginLoanOrdersRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.margin.IsolatedMarginRepayLoanRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.margin.IsolatedMarginTransferRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.isolatedmargin.IsolatedMarginAccount;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.isolatedmargin.IsolatedMarginLoadOrder;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.isolatedmargin.IsolatedMarginSymbolInfo;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiIsolatedMarginService;

public interface IsolatedMarginClient {

  Long transfer(IsolatedMarginTransferRequest request);

  Long applyLoan(IsolatedMarginApplyLoanRequest request);

  Long repayLoan(IsolatedMarginRepayLoanRequest request);

  List<IsolatedMarginLoadOrder> getLoanOrders(IsolatedMarginLoanOrdersRequest request);

  List<IsolatedMarginAccount> getLoanBalance(IsolatedMarginAccountRequest request);

  List<IsolatedMarginSymbolInfo> getLoanInfo(IsolatedMarginLoanInfoRequest request);

  static IsolatedMarginClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiIsolatedMarginService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
