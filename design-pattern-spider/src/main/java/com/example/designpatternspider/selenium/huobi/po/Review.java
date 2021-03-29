package com.example.designpatternspider.selenium.huobi.po;

import java.math.BigDecimal;
import java.util.Date;

import com.example.designpatternspider.selenium.huobi.po.indicator.KDJ;
import com.example.designpatternspider.selenium.huobi.po.indicator.MACD;
import com.example.designpatternspider.selenium.huobi.po.indicator.RSI;

import lombok.Data;

@Data
public class Review {

    private BigDecimal price;
    private Date time;
    private MACD macd;
    private KDJ kdj;
    private RSI rsi;


}
