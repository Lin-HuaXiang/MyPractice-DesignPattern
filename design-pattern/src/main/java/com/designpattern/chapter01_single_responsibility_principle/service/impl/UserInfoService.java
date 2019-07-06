package com.designpattern.chapter01_single_responsibility_principle.service.impl;


import com.designpattern.chapter01_single_responsibility_principle.biz.IUserBiz;
import com.designpattern.chapter01_single_responsibility_principle.biz.IUserBO;
import com.designpattern.chapter01_single_responsibility_principle.biz.IUserInfo;
import com.designpattern.chapter01_single_responsibility_principle.biz.impl.UserInfo;
import com.designpattern.chapter01_single_responsibility_principle.service.IUserInfoService;

public class UserInfoService implements IUserInfoService {


    public void deleteUser() {
        IUserInfo userInfo = new UserInfo();
        IUserBO userBO = (IUserBO)userInfo;
        userBO.setPassword("abc");
        IUserBiz userBiz = (IUserBiz)userInfo;
        userBiz.deleteUser(userBO);
    }
}
