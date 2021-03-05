package com.example.designpatternfactory.service;

import lombok.Data;

@Data
public class DeliverReq {

    private String userName;

    private String userPhone;

    private String sku;

    private String orderId;

    private String consigneeUserName;

    private String consigneeUserPhone;

    private String consigneeUserAddress;
    
}
