package com.example.designpatternadaptor.mq;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class POPOrderDelivered {

    private String uid;
    private String orderId;
    private Date orderTime;
    private Date sku;
    private String skuName;
    private BigDecimal decimal;
    
}
