package com.example.designpatternspider.selenium.huobi.chain.futures.usdt.fil.indicator;

import lombok.Data;

@Data
public class TradeSignal {

    private Boolean signalOpenLong = true;
    private Boolean signalOpenShort = true;
    private Boolean signalCloseLong = true;
    private Boolean signalCloseShort = true;

    public void reset() {
        signalOpenLong = true;
        signalOpenShort = true;
        signalCloseLong = true;
        signalCloseShort = true;
    }
}
