package com.example.designpatternspider.selenium.huobi.mock;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.example.designpatternspider.selenium.huobi.po.Signal;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewDataMockMacd extends ReviewDataMockIndicator {

    private Queue<BigDecimal> macdSerial = new ArrayBlockingQueue<>(3);

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
        } else if (macd.compareTo(BigDecimal.ZERO) <= 0 && lastMacd.compareTo(BigDecimal.ZERO) >= 0) {
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


        // 如果连续三个相同符号，买入

        if (!macdSerial.offer(macd)) {
            macdSerial.remove();
            macdSerial.add(macd);
        }
        if (macdSerial.size() == 3) {
            BigDecimal num1 = macdSerial.poll();
            BigDecimal num2 = macdSerial.poll();
            BigDecimal num3 = macdSerial.poll();
            if (num3.compareTo(num2) > 0 && num2.compareTo(num1) > 0 && num1.compareTo(BigDecimal.ZERO) > 0) {
                // 买多，并且清空队列
                signalOpenLong = true;
                signalCloseShort = true;
                log.info("three continuous buy long");
            } else if (num3.compareTo(num2) < 0 && num2.compareTo(num1) < 0 && num1.compareTo(BigDecimal.ZERO) < 0) {
                // 买空，并且清空队列
                signalCloseLong = true;
                signalOpenShort = true;
                log.info("three continuous buy short");
            } else {
                macdSerial.offer(num1);
                macdSerial.offer(num2);
                macdSerial.offer(num3);
            }
        }

        // print
        log.info("LP{}->P{} LDIF{}->DIF{} LMACD{}->MACD{}", lastPrice, price, lastDif, dif, lastMacd, macd);
        lastDif = dif;
        lastMacd = macd;
        lastPrice = price;
        return new Signal(signalOpenLong, signalOpenShort, signalCloseLong, signalCloseShort);
    }

}
