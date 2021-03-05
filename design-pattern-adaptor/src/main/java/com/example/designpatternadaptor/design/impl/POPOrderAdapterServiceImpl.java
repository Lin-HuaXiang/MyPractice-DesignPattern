package com.example.designpatternadaptor.design.impl;

import com.example.designpatternadaptor.design.OrderAdapterService;
import com.example.designpatternadaptor.service.POPOrderService;

public class POPOrderAdapterServiceImpl implements OrderAdapterService {

    private POPOrderService popOrderService = new POPOrderService();

    public boolean isFirst(String uid) {
        return popOrderService.isFirstOrder(uid);
    }
    
}
