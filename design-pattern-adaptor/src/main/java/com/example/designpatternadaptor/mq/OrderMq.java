package com.example.designpatternadaptor.mq;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMq {
    
    private String uid;
    private String sku;
    private String orderId;
    private Date createOrderTime;
    
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
