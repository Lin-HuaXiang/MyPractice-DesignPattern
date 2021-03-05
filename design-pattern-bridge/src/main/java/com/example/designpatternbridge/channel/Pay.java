package com.example.designpatternbridge.channel;

import java.math.BigDecimal;

import com.example.designpatternbridge.mode.IPayMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Pay {

    protected Logger logger = LoggerFactory.getLogger(Pay.class);

    protected IPayMode payMode;

    protected Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    public abstract String transfer(String uId, String tradeId, BigDecimal amount);
    

}
