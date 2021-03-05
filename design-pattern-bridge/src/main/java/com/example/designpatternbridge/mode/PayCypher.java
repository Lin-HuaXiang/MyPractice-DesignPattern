package com.example.designpatternbridge.mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayCypher implements IPayMode {

    @Override
    public boolean security(String uId) {
        log.info("密码⽀支付，⻛风控校验环境安全");
        return true;
    }

}
