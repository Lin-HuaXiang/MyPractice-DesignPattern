package com.example.designpatternspider.selenium.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.example.designpatternspider.selenium.huobi.po.Data;

import org.springframework.util.CollectionUtils;

public class RsiIndicator {

    public static BigDecimal calcRsi(List<Data> dataList) {
        BigDecimal upSum = BigDecimal.ZERO;
        BigDecimal downSum = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(dataList)) {
            // RSI=100-[100/(1+RS)]
            for (Data data : dataList) {
                BigDecimal subtract = data.getClose().subtract(data.getOpen());
                if (subtract.compareTo(BigDecimal.ZERO) > 0) {
                    // 上升数值
                    upSum = upSum.add(subtract);
                } else if (subtract.compareTo(BigDecimal.ZERO) < 0) {
                    // 下降数值
                    downSum = downSum.add(subtract.abs());
                }
            }
            int len = dataList.size();
            return calcRsi(upSum, downSum, len);
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal calcRsi(BigDecimal upSum, BigDecimal downSum, int size) {
        // up Sum
        BigDecimal upRs = upSum.divide(BigDecimal.valueOf(size), 6, RoundingMode.HALF_DOWN);
        // down Sum
        BigDecimal downRs = downSum.divide(BigDecimal.valueOf(size), 6, RoundingMode.HALF_DOWN);
        // RS
        BigDecimal rs;
        if (downRs.compareTo(BigDecimal.ZERO) == 0) {
            rs = BigDecimal.ZERO;
        } else {
            rs = upRs.divide(downRs, 6, RoundingMode.HALF_DOWN);
        }
        // 1+RS
        BigDecimal onePlusRs = BigDecimal.valueOf(1).add(rs);
        // 100/(1+RS)
        BigDecimal hundredDev = BigDecimal.valueOf(100).divide(onePlusRs, 6, RoundingMode.HALF_DOWN);
        // 100-[100/(1+RS)]
        return BigDecimal.valueOf(100).subtract(hundredDev);
    }

}
