package com.example.designpatternspider.selenium.huobi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Signal {
    
    private Boolean signalOpenLong = false;
    private Boolean signalOpenShort = false;
    private Boolean signalCloseLong = false;
    private Boolean signalCloseShort = false;


    /**
     * unite
     * @param signal
     * @return
     */
    public void unite(Signal signal) {
        this.signalOpenLong |= signal.getSignalOpenLong();
        this.signalOpenShort |= signal.getSignalOpenShort();
        this.signalCloseLong |= signal.getSignalCloseLong();
        this.signalCloseShort |= signal.getSignalCloseShort();
    }

    /**
     * intersect
     * @param signal
     * @return
     */
    public void intersect(Signal signal) {
        this.signalOpenLong &= signal.getSignalOpenLong();
        this.signalOpenShort &= signal.getSignalOpenShort();
        this.signalCloseLong &= signal.getSignalCloseLong();
        this.signalCloseShort &= signal.getSignalCloseShort();
    }
}
