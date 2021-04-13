package com.example.designpatternspider.selenium.huobi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

public class MergeDataUtil {

    static String[] periods = { "1min", "5min", "15min", "60min", "4hour", "1day" };

    public static void mergeLowerData(List<ReviewExport> listData, String currency, String period) {

        String lowerPeriod = "";
        for (int i = 1; i < periods.length; i++) {
            if (periods[i].equals(period)) {
                lowerPeriod = periods[i - 1];
                break;
            }
        }
        List<ReviewExport> lowerListData = ReviewDataUtil.getLocalData(currency, lowerPeriod);
        for (int i = listData.size() - 1; i > 0; i--) {
            long time = Long.parseLong(listData.get(i).getTime().replace("-", "").replace(" ", "").replace(":", ""));
            long nextTime = Long.parseLong(listData.get(i - 1).getTime().replace("-", "").replace(" ", "").replace(":", ""));
            for (ReviewExport lower : lowerListData) {
                long lowerTime = Long.parseLong(lower.getTime().replace("-", "").replace(" ", "").replace(":", ""));
                if (lowerTime > nextTime && lowerTime < time) {
                    List<ReviewExport> lowerData = listData.get(i - 1).getLowerData();
                    lowerData = Optional.ofNullable(lowerData).orElse(new ArrayList<>());
                    lowerData.add(lower);
                    listData.get(i - 1).setLowerData(lowerData);
                }
            }
        }
    }

}
