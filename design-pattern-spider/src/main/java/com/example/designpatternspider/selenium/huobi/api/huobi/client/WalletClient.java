package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.wallet.CreateWithdrawRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.wallet.DepositAddressRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.wallet.DepositWithdrawRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.wallet.WithdrawAddressRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.wallet.WithdrawQuotaRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.wallet.DepositAddress;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.wallet.DepositWithdraw;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.wallet.WithdrawAddressResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.wallet.WithdrawQuota;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiWalletService;

public interface WalletClient {

  List<DepositAddress> getDepositAddress(DepositAddressRequest request);

  WithdrawQuota getWithdrawQuota(WithdrawQuotaRequest request);

  WithdrawAddressResult getWithdrawAddress(WithdrawAddressRequest request);

  Long createWithdraw(CreateWithdrawRequest request);

  Long cancelWithdraw(Long withdrawId);

  List<DepositWithdraw> getDepositWithdraw(DepositWithdrawRequest request);

  static WalletClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiWalletService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
