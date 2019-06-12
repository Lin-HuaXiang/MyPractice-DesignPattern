package com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.impl;

import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IConnectionManager;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IDataTransfer;

public class Phone implements IDataTransfer, IConnectionManager {

    public void dial(String phoneNumber) {

    }

    public void handUp() {

    }

    public void dataTransfer(IConnectionManager cm) {

    }
}
