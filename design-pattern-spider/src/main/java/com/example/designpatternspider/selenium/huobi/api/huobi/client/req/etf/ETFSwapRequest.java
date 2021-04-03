package com.example.designpatternspider.selenium.huobi.api.huobi.client.req.etf;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.EtfSwapDirectionEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ETFSwapRequest {

  private EtfSwapDirectionEnum direction;

  private String etfName;

  private BigDecimal amount;

}