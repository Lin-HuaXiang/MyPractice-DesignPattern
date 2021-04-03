package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.crossmargin.CrossMarginApplyLoanRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.crossmargin.CrossMarginLoanOrdersRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.crossmargin.CrossMarginRepayLoanRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.crossmargin.CrossMarginTransferRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.crossmargin.GeneralLoanOrdersRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.crossmargin.GeneralRepayLoanRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.crossmargin.CrossMarginAccount;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.crossmargin.CrossMarginCurrencyInfo;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.crossmargin.CrossMarginLoadOrder;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.crossmargin.GeneralRepayLoanRecord;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.crossmargin.GeneralRepayLoanResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiCrossMarginService;

public interface CrossMarginClient {

  Long transfer(CrossMarginTransferRequest request);

  Long applyLoan(CrossMarginApplyLoanRequest request);

  void repayLoan(CrossMarginRepayLoanRequest request);

  List<CrossMarginLoadOrder> getLoanOrders(CrossMarginLoanOrdersRequest request);

  CrossMarginAccount getLoanBalance();

  List<CrossMarginCurrencyInfo> getLoanInfo();

  static CrossMarginClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiCrossMarginService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }

  List<GeneralRepayLoanResult> repayLoan(GeneralRepayLoanRequest request);

  List<GeneralRepayLoanRecord> getRepaymentLoanRecords(GeneralLoanOrdersRequest request);

}
