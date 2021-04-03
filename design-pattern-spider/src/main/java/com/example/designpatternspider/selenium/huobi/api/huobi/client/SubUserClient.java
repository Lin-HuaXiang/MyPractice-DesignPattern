package com.example.designpatternspider.selenium.huobi.api.huobi.client;

import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.account.TransferSubuserRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.GetApiKeyListRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.GetSubUserAccountListRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.GetSubUserDepositRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.GetSubUserListRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.SubUserApiKeyDeletionRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.SubUserApiKeyGenerationRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.SubUserApiKeyModificationRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.SubUserCreationRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.SubUserManagementRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.SubUserTradableMarketRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser.SubUserTransferabilityRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Options;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.ExchangeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.exception.SDKException;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.AccountBalance;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.account.SubuserAggregateBalance;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.GetApiKeyListResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.GetSubUserAccountListResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.GetSubUserDepositResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.GetSubUserListResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserApiKeyGenerationResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserApiKeyModificationResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserCreationInfo;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserManagementResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserState;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserTradableMarketResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.subuser.SubUserTransferabilityResult;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.wallet.DepositAddress;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiSubUserService;

public interface SubUserClient {


  List<SubUserCreationInfo> subuserCreation(SubUserCreationRequest request);


  GetSubUserListResult getSubUserList(GetSubUserListRequest request);

  SubUserState getSubuserState(Long subUid);

  SubUserManagementResult subuserManagement(SubUserManagementRequest request);

  GetSubUserAccountListResult getSubuserAccountList(GetSubUserAccountListRequest request);

  SubUserTransferabilityResult subuserTransferability(SubUserTransferabilityRequest request);

  SubUserTradableMarketResult subuserTradableMarket(SubUserTradableMarketRequest request);

  SubUserApiKeyGenerationResult subuserApiKeyGeneration(SubUserApiKeyGenerationRequest request);

  SubUserApiKeyModificationResult subuserApiKeyModification(SubUserApiKeyModificationRequest request);

  void subuserApiKeyDeletion(SubUserApiKeyDeletionRequest request);

  GetApiKeyListResult getApiKeyList(GetApiKeyListRequest request);

  List<DepositAddress> getSubUserDepositAddress(Long subUid, String currency);

  GetSubUserDepositResult getSubUserDeposit(GetSubUserDepositRequest request);
  /**
   * Transfer to sub-user
   * @param request
   * @return
   */
  long transferSubuser(TransferSubuserRequest request);

  /**
   * Get sub-user's account balance
   * @param subuserId
   * @return
   */
  List<AccountBalance> getSubuserAccountBalance(Long subuserId);

  /**
   * Get the aggregated balance of all sub-accounts of the current user.
   * @return
   */
  List<SubuserAggregateBalance> getSubuserAggregateBalance();

  static SubUserClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiSubUserService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }

  long getUid();
}
