package com.example.designpatternspider.selenium.huobi.mock;

import com.example.designpatternspider.selenium.huobi.po.Signal;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

public abstract class ReviewDataMockIndicator {
    
    protected Boolean signalCloseShort = false;
    protected Boolean signalOpenLong = false;
    protected Boolean signalCloseLong = false;
    protected Boolean signalOpenShort = false;

    public Boolean getSignalCloseLong() {
        return signalCloseLong;
    }
    public Boolean getSignalOpenLong() {
        return signalOpenLong;
    }
    public Boolean getSignalCloseShort() {
        return signalCloseShort;
    }
    public Boolean getSignalOpenShort() {
        return signalOpenShort;
    }

    public abstract Signal calcMock(ReviewExport reviewExport);

    public void resetSignal() {
        signalCloseShort = false;
        signalOpenLong = false;
        signalCloseLong = false;
        signalOpenShort = false;
    }
}
