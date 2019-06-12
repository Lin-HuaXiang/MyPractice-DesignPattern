package com.designpattern.chapter01_SingleResponsibilityPrinciple.service.impl;


import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IUserBiz;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IUserBO;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IUserInfo;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.impl.UserInfo;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.service.IUserInfoService;

public class UserInfoService implements IUserInfoService {


    public void deleteUser() {
        IUserInfo userInfo = new UserInfo();
        IUserBO userBO = (IUserBO)userInfo;
        userBO.setPassword("abc");
        IUserBiz userBiz = (IUserBiz)userInfo;
        userBiz.deleteUser(userBO);
    }
}
