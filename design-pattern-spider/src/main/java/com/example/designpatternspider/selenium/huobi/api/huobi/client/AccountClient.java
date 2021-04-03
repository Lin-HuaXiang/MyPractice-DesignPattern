package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountAssetValuationRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountBalanceRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountFuturesTransferRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountHistoryRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountLedgerRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountTransferRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.PointRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.PointTransferRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.SubAccountUpdateRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.Account;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountAssetValuationResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountBalance;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountFuturesTransferResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountHistory;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountLedgerResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountTransferResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountUpdateEvent;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.Point;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.PointTransferResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserState;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiAccountService;
import com.example.designpatternspider.selenium.huobi.api.huobi.utils.ResponseCallback;

public interface AccountClient {

  /**
   * Get User Account List
   * @return
   */
  List<Account> getAccounts();

  /**
   * Get User Account Balance
   * @param request
   * @return
   */
  AccountBalance getAccountBalance(AccountBalanceRequest request);

  List<AccountHistory> getAccountHistory(AccountHistoryRequest request);

  AccountLedgerResult getAccountLedger(AccountLedgerRequest request);

  AccountTransferResult accountTransfer(AccountTransferRequest request);

  AccountFuturesTransferResult accountFuturesTransfer(AccountFuturesTransferRequest request);

  Point getPoint(PointRequest request);

  PointTransferResult pointTransfer(PointTransferRequest request);

  AccountAssetValuationResult accountAssetValuation(AccountAssetValuationRequest request);

  void subAccountsUpdate(SubAccountUpdateRequest request, ResponseCallback<AccountUpdateEvent> callback);

  static AccountClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiAccountService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
