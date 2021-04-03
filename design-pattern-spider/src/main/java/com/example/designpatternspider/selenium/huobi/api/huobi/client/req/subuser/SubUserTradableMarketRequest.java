package com.example.designpatternspider.selenium.huobi.api.huobi.client.req.subuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.TradableMarketAccountTypeEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.TradableMarketActivationEnums;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubUserTradableMarketRequest {

  private String subUids;

  private TradableMarketAccountTypeEnum accountType;

  private TradableMarketActivationEnums activation;

}
