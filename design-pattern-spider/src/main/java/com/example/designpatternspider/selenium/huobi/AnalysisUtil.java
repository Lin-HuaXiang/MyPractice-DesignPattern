package com.example.designpatternspider.selenium.huobi;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnalysisUtil {

    // Use the buy-sell price difference to record profits

    static Integer duoCount = 0;
    static Integer kongCount = 0;
    static Integer duoRepository = 10;
    static Integer kongRepository = 10;
    static BigDecimal sum = new BigDecimal("100");

    // Find the maximum profit, the principal is 10000.
    // Every price point does not know the size of the next price point.
    // Maximum 10 purchases.

    public static void main(String[] args) {
 
        work1();
    }

    public static void work1() { 
        String sig = "";
        String lastSig = "";
        BigDecimal dev = BigDecimal.ZERO;
        BigDecimal lastDev = BigDecimal.ZERO;
        BigDecimal mac = BigDecimal.ZERO;
        BigDecimal lastMac = BigDecimal.ZERO;
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal lastPrice = BigDecimal.ZERO;
        BigDecimal lastSubDev = BigDecimal.ZERO;
        BigDecimal lastSubMac = BigDecimal.ZERO;

        int n = DataInfo.PRICE.length;

        for (int i = 0; i < n; i++) {

            dev = BigDecimal.valueOf(DataInfo.DEV[i]);
            mac = BigDecimal.valueOf(DataInfo.MACD[i]);
            price = BigDecimal.valueOf(DataInfo.PRICE[i]);

            if (i == 0) {
                lastDev = dev;
                lastPrice = price;
                lastMac = mac;
            }

            // Calculate buying and selling points

            // If the deviation changes from a negative number to a positive number, then
            // buy duo
            if (dev.compareTo(BigDecimal.ZERO) > 0 && lastDev.compareTo(BigDecimal.ZERO) < 0) {
                sellKongAll(i);
                buyDuo50Percent(i);
            }
            // If the deviation changes from a positive number to a negative number, then
            // buy kong
            if (dev.compareTo(BigDecimal.ZERO) < 0 && lastDev.compareTo(BigDecimal.ZERO) > 0) {
                sellDuoAll(i);
                buyKong50Percent(i);
            }

            BigDecimal subDev = dev.subtract(lastDev);
            BigDecimal subPrice = price.subtract(lastPrice);
            BigDecimal subMac = mac.subtract(lastMac);

            // up channel
            if (dev.compareTo(BigDecimal.ZERO) > 0) {

                // if the deviation ratio is relatively small, then ignore; 0~10 ignore 30~max
                // if two consecutive deviations decrease, then sell;
                // sell
                if (dev.compareTo(lastDev) < 0) {
                    sellDuoAll(i);
                    buyKong50Percent(i);
                }

                if (lastSubDev.compareTo(BigDecimal.ZERO) < 0 && lastSubMac.compareTo(BigDecimal.ZERO) < 0
                        && subDev.compareTo(BigDecimal.ZERO) > 0 && subMac.compareTo(BigDecimal.ZERO) > 0) {
                    sellKongAll(i);
                    buyDuo50Percent(i);
                }

            }

            // down channel
            if (dev.compareTo(BigDecimal.ZERO) < 0) {

                if (dev.compareTo(lastDev) > 0) {
                    sellKongAll(i);
                    buyDuo50Percent(i);
                }

                if (lastSubDev.compareTo(BigDecimal.ZERO) > 0 && lastSubMac.compareTo(BigDecimal.ZERO) > 0
                        && subDev.compareTo(BigDecimal.ZERO) < 0 && subMac.compareTo(BigDecimal.ZERO) < 0) {
                    sellDuoAll(i);
                    buyKong50Percent(i);
                }
            }

            BigDecimal subtractPrice = price.subtract(lastPrice);
            sum = sum.add(subtractPrice.multiply(BigDecimal.valueOf(duoCount)))
                    .add(subtractPrice.multiply(BigDecimal.valueOf(kongCount)).multiply(BigDecimal.valueOf(-1)));

            log.info("current sum {}, price {} dev {} mac {} duoCount {} kongCount {}", sum, price, dev, mac, duoCount,
                    kongCount);

            lastDev = dev;
            lastMac = mac;
            lastPrice = price;
            lastSig = sig;
            lastSubDev = subDev;
            lastSubMac = subMac;
        }
    }

    public static void buyDuo50Percent(Integer i) {
        int use = duoRepository / 2;
        duoRepository -= use;
        duoCount += use;
        // if (use > 0) {
        //     double p = DataInfo.PRICE[i];
        //     // sum = sum.subtract(BigDecimal.valueOf(p).multiply(BigDecimal.valueOf(use)));
        // }
    }

    public static void sellDuoAll(Integer i) {
        duoRepository += duoCount;
        int use = duoCount;
        duoCount = 0;
        // if (use > 0) {
        //     double p = DataInfo.PRICE[i];
        //     // sum = sum.add(BigDecimal.valueOf(p).multiply(BigDecimal.valueOf(use)));
        // }
    }

    public static void buyKong50Percent(Integer i) {
        int use = kongRepository / 2;
        kongRepository -= use;
        kongCount += use;
        // if (use > 0) {
        //     double p = DataInfo.PRICE[i];
        //     // sum = sum.subtract(BigDecimal.valueOf(p).multiply(BigDecimal.valueOf(use)));
        // }
    }

    public static void sellKongAll(Integer i) {
        kongRepository += kongCount;
        int use = kongCount;
        kongCount = 0;
        // if (use > 0) {
        //     double p = DataInfo.PRICE[i];
        //     // sum = sum.add(BigDecimal.valueOf(p).multiply(BigDecimal.valueOf(use)));
        // }
    }
}
