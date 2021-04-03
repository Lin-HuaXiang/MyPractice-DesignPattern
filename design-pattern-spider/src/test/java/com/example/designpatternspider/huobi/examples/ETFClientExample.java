package com.example.designpatternspider.huobi.examples;

import java.math.BigDecimal;
import java.util.List;

import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.etf.ETFSwapListRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.client.req.etf.ETFSwapRequest;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.Constants;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.HuobiOptions;
import com.example.designpatternspider.selenium.huobi.api.huobi.constant.enums.EtfSwapDirectionEnum;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.etf.ETFConfig;
import com.example.designpatternspider.selenium.huobi.api.huobi.model.etf.ETFSwapRecord;
import com.example.designpatternspider.selenium.huobi.api.huobi.service.huobi.HuobiETFService;

public class ETFClientExample {

  public static void main(String[] args) {
    HuobiETFService etfService = new HuobiETFService(HuobiOptions.builder()
        .apiKey(Constants.API_KEY)
        .secretKey(Constants.SECRET_KEY)
        .build());

    String etfName = "hb10";

    ETFConfig etfConfig = etfService.getConfig(etfName);
    System.out.println(etfConfig.toString());
    etfConfig.getUnitPriceList().forEach(etfUnitPrice -> {
      System.out.println("unit pirce:" + etfUnitPrice.toString());
    });

    etfService.etfSwap(ETFSwapRequest.builder()
        .direction(EtfSwapDirectionEnum.SWAP_IN_ETF)
        .etfName(etfName)
        .amount(new BigDecimal(1000))
        .build());

    etfService.etfSwap(ETFSwapRequest.builder()
        .direction(EtfSwapDirectionEnum.SWAP_OUT_ETF)
        .etfName(etfName)
        .amount(new BigDecimal(1000))
        .build());

    List<ETFSwapRecord> recordList = etfService.getEtfSwapList(ETFSwapListRequest.builder()
        .etfName(etfName)
        .offset(0)
        .limit(100)
        .build());
    recordList.forEach(record->{
      System.out.println(record.toString());
    });



  }

}
