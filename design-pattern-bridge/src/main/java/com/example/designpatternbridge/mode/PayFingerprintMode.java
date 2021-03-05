package com.example.designpatternbridge.mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayFingerprintMode implements IPayMode {

    @Override
    public boolean security(String uId) {
        log.info("指纹⽀支付，⻛风控校验指纹信息");
        return true;
    }
    
    
}
