package com.example.designpatternspider.selenium.huobi.api.huobi.client.req.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.DepthLevels;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubMbpRefreshUpdateRequest {

  private String symbols;

  private DepthLevels levels;

}
