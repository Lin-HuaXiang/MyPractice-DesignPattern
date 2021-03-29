package com.example.designpatternspider.selenium.huobi.po.indicator;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KDJ {

    private BigDecimal k;
    private BigDecimal d;
    private BigDecimal j;
    
}
