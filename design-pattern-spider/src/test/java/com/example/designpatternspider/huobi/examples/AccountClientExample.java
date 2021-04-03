package com.example.designpatternspider.huobi.examples;

import java.math.BigDecimal;
import java.util.List;

import com.example.designpatternspider.huobi.Constants;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.AccountClient;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountAssetValuationRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountBalanceRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountFuturesTransferRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountHistoryRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountLedgerRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.AccountTransferRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.PointRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.PointTransferRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.SubAccountUpdateRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.TransferSubuserRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.HuobiOptions;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.AccountFuturesTransferTypeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.AccountTransferAccountTypeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.AccountTypeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.AccountUpdateModeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.TransferMasterTypeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.Account;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountAssetValuationResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountBalance;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountFuturesTransferResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountHistory;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountLedgerResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountTransferResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.Point;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.PointTransferResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.SubuserAggregateBalance;

public class AccountClientExample {

  public static void main(String[] args) {

    Long accountId = 123L;
    AccountClient accountService = AccountClient.create(HuobiOptions.builder()
        .apiKey(Constants.API_KEY)
        .secretKey(Constants.SECRET_KEY)
        .build());

    List<Account> accountList = accountService.getAccounts();
    accountList.forEach(account -> {
      System.out.println(account.toString());
    });


    AccountBalance accountBalance = accountService.getAccountBalance(AccountBalanceRequest.builder()
        .accountId(accountId)
        .build());

    System.out.println(accountBalance.getId());
    System.out.println(accountBalance.getType());
    System.out.println(accountBalance.getState());
    accountBalance.getList().forEach(balance -> {
      System.out.println(balance.toString());
    });

    List<AccountHistory> historyList = accountService.getAccountHistory(AccountHistoryRequest.builder().accountId(accountId).build());
    historyList.forEach(history->{
      System.out.println(history);
    });

    AccountLedgerResult accountLedgerResult = accountService.getAccountLedger(AccountLedgerRequest.builder()
        .accountId(accountId)
        .limit(2)
        .build());
    System.out.println("leger nextId: " + accountLedgerResult.getNextId());
    accountLedgerResult.getLedgerList().forEach(ledger -> {
      System.out.println(ledger);
    });


    accountService.subAccountsUpdate(SubAccountUpdateRequest.builder()
        .accountUpdateMode(AccountUpdateModeEnum.ACCOUNT_CHANGE).build(), event -> {
      System.out.println(event.toString());
    });


    AccountTransferResult accountTransferResult = accountService.accountTransfer(AccountTransferRequest.builder()
        .fromUser(123L)
        .fromAccount(456L)
        .fromAccountType(AccountTransferAccountTypeEnum.SPOT)
        .toUser(678L)
        .toAccount(789L)
        .toAccountType(AccountTransferAccountTypeEnum.MARGIN)
        .currency("usdt")
        .amount(new BigDecimal("10"))
        .build());

    System.out.println("account transfer result:"+accountTransferResult.toString());

    AccountFuturesTransferResult accountFuturesTransferResult = accountService.accountFuturesTransfer(AccountFuturesTransferRequest.builder()
        .currency("xrp")
        .amount(new BigDecimal("5"))
        .type(AccountFuturesTransferTypeEnum.PRO_TO_FUTURES)
        .build());

    System.out.println("account futures result:"+accountFuturesTransferResult.toString());

    Point point = accountService.getPoint(PointRequest.builder().build());
    System.out.println("get point: " + point);


    PointTransferResult pointTransferResult = accountService.pointTransfer(PointTransferRequest.builder()
      .fromUid(123L)
      .toUid(123L)
      .groupId(123L)
      .amount(BigDecimal.ONE)
      .build());
    System.out.println(pointTransferResult);

    AccountAssetValuationResult accountAssetValuationResult = accountService.accountAssetValuation(AccountAssetValuationRequest.builder().accountType(AccountTypeEnum.SPOT).build());
    System.out.println(accountAssetValuationResult);
  }

}
