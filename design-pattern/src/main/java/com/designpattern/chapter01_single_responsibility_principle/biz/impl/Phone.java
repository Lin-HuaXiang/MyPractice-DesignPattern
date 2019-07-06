package com.designpattern.chapter01_single_responsibility_principle.biz.impl;

import com.designpattern.chapter01_single_responsibility_principle.biz.IConnectionManager;
import com.designpattern.chapter01_single_responsibility_principle.biz.IDataTransfer;

public class Phone implements IDataTransfer, IConnectionManager {

    public void dial(String phoneNumber) {

    }

    public void handUp() {

    }

    public void dataTransfer(IConnectionManager cm) {

    }
}
