package com.example.designpatternspider.selenium.huobi.market;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;
import com.example.designpatternspider.selenium.huobi.util.ReviewDataUtil;

public class UnilateralMarket implements MarketStrategy {
    
    // 4 hour macd, macd > 0 only open long, macd < 0 only open short.

    // private String currency;
    private String period = "4hour";

    private Map<String, String> marketStatusMap = new HashMap<>();

    public Map<String, String> getMarketStatusMap() {
        return marketStatusMap;
    }

    public void load4HourData(String currency) {
        List<ReviewExport> listData = ReviewDataUtil.getLocalData(currency, period);
        Map<String, String>  newMarketStatusMap = new HashMap<>();
        for (ReviewExport reviewExport : listData) {
            String key = reviewExport.getTime().replace("-", "").replace(" ", "").replace(":", "");
            key = key.substring(0, key.length() - 4);
            String value = reviewExport.getMacd().compareTo(BigDecimal.ZERO) > 0 ? "long" : "short";
            newMarketStatusMap.put(key, value);
        }
        this.marketStatusMap = newMarketStatusMap;
    }


    public static void main(String[] args) {
        UnilateralMarket m = new UnilateralMarket();
        m.load4HourData("trxusdt");
        Map<String, String> marketStatusMap2 = m.getMarketStatusMap();
        System.out.println(marketStatusMap2);
    }

    
}
