package com.example.designpatternbridge.mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayFaceMode implements IPayMode {

    @Override
    public boolean security(String uId) {
        log.info("⼈人脸⽀支付，⻛风控校验脸部识别");
        return true;
    }


    
}
