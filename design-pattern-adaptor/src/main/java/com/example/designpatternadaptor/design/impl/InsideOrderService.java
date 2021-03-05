package com.example.designpatternadaptor.design.impl;

import com.example.designpatternadaptor.design.OrderAdapterService;
import com.example.designpatternadaptor.service.OrderService;

public class InsideOrderService implements OrderAdapterService {

    private OrderService orderService = new OrderService();

    @Override
    public boolean isFirst(String uid) {
        return orderService.queryUserOrderCount(uid) <= 1;
    }

   
    
}
