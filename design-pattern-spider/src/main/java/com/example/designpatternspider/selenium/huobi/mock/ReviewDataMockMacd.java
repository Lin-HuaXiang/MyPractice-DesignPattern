package com.example.designpatternspider.selenium.huobi.mock;

import java.math.BigDecimal;

import com.example.designpatternspider.selenium.huobi.po.Signal;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewDataMockMacd extends ReviewDataMockIndicator {

    private BigDecimal lastDif;
    private BigDecimal lastMacd;
    private BigDecimal lastPrice;
    private BigDecimal lastSubMacd = BigDecimal.ZERO;
    private BigDecimal lastSubDif = BigDecimal.ZERO;

    public Signal calcMock(ReviewExport reviewExport) {

        BigDecimal dif = reviewExport.getDif();
        BigDecimal macd = reviewExport.getMacd();
        BigDecimal price = reviewExport.getPrice();

        // back to main iframe
        // Initialize the last price on the first call
        if (ObjectUtils.isEmpty(lastMacd)) {
            lastDif = dif;
            lastMacd = macd;
            lastPrice = price;
        }

        BigDecimal subDif = dif.subtract(lastDif);
        BigDecimal subMacd = macd.subtract(lastMacd);
        BigDecimal subPrice = price.subtract(lastPrice);

        if (macd.compareTo(BigDecimal.ZERO) >= 0 && lastMacd.compareTo(BigDecimal.ZERO) <= 0) {
            // log.info("macd macd gold fork");
            signalCloseShort = true;
            signalOpenLong = true;
        }

        // If the deviation changes from a positive number to a negative number, then
        if (macd.compareTo(BigDecimal.ZERO) <= 0 && lastMacd.compareTo(BigDecimal.ZERO) >= 0) {
            // log.info("macd death fork");
            signalCloseLong = true;
            signalOpenShort = true;
        }

        // up channel
        if (macd.compareTo(BigDecimal.ZERO) > 0) {

            if (macd.compareTo(lastMacd) < 0 || dif.compareTo(lastDif) < 0) {
                // log.info("macd up channel, trend turns negative");
                signalCloseLong = true;
            }

            if ((lastSubMacd.compareTo(BigDecimal.ZERO) <= 0 && subMacd.compareTo(BigDecimal.ZERO) > 0)
                    || (lastSubDif.compareTo(BigDecimal.ZERO) <= 0 && subDif.compareTo(BigDecimal.ZERO) > 0)) {
                // log.info("macd up channel, trend turns positive");
                signalCloseShort = true;
                signalOpenLong = true;
            }

        }

        // down channel
        if (macd.compareTo(BigDecimal.ZERO) < 0) {

            if (macd.compareTo(lastMacd) > 0 || dif.compareTo(lastDif) > 0) {
                // log.info("macd down channel, trend turns negative");
                signalCloseShort = true;
            }

            if ((lastSubMacd.compareTo(BigDecimal.ZERO) >= 0 && subMacd.compareTo(BigDecimal.ZERO) < 0)
                    || (lastSubDif.compareTo(BigDecimal.ZERO) >= 0 && subDif.compareTo(BigDecimal.ZERO) < 0)) {
                // log.info("macd down channel, trend turns positive");
                signalCloseLong = true;
                signalOpenShort = true;
            }
        }

        // print
        log.info("P{}LP{}D{}LD{}MA{}LMA{}", price, lastPrice, dif, lastDif, macd, lastMacd);
        lastDif = dif;
        lastMacd = macd;
        lastPrice = price;
        return new Signal(signalOpenLong, signalOpenShort, signalCloseLong, signalCloseShort);
    }

    

}
