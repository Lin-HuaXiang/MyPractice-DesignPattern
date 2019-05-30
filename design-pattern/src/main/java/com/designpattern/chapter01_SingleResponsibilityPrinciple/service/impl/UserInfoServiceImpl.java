package com.designpattern.chapter01_SingleResponsibilityPrinciple.service.impl;

import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IUserBiz;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.bo.IUserBO;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.pojo.IUserInfo;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.pojo.impl.UserInfo;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.service.IUserInfoService;

public class UserInfoServiceImpl implements IUserInfoService {


    public void deleteUser() {
        IUserInfo userInfo = new UserInfo();
        IUserBO userBO = (IUserBO)userInfo;
        userBO.setPassword("abc");
        IUserBiz userBiz = (IUserBiz)userInfo;
        userBiz.deleteUser(userBO);
    }
}
