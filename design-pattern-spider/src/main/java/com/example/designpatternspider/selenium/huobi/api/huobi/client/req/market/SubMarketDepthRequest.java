package com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.DepthStepEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubMarketDepthRequest {
  private String symbol;

  private DepthStepEnum step;

}
