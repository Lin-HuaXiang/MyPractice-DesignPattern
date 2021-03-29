package com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator;

import lombok.Data;

@Data
public class TradeSignal {

    private Boolean signalOpenLong = false;
    private Boolean signalOpenShort = false;
    private Boolean signalCloseLong = false;
    private Boolean signalCloseShort = false;

    public void reset() {
        signalOpenLong = false;
        signalOpenShort = false;
        signalCloseLong = false;
        signalCloseShort = false;
    }
}
