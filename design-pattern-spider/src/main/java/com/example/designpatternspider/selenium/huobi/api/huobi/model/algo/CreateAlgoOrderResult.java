package com.example.designpatternspider.selenium.huobi.api.huobi.model.algo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAlgoOrderResult {

  private String clientOrderId;

}
