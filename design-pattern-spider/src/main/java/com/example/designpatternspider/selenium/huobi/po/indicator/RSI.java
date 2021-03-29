package com.example.designpatternspider.selenium.huobi.po.indicator;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RSI {

    private BigDecimal calcRsi9;
    private BigDecimal calcRsi12;
    private BigDecimal calcRsi14;
    private BigDecimal calcRsi72;
    
}
