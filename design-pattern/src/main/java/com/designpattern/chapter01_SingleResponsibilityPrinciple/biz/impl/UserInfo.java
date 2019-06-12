package com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.impl;

import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IUserBO;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IUserInfo;

public class UserInfo implements IUserInfo {

    public boolean changePassword(String password) {
        return false;
    }

    public boolean deleteUser(IUserBO userBO) {

        return false;
    }

    public void mapUser(IUserBO userBO) {

    }

    public boolean addOrg(IUserBO userBO, int orgID) {
        return false;
    }

    public boolean addRole(IUserBO userBO, int roleID) {
        return false;
    }

    public void setUserID(String userID) {

    }

    public String getUserID() {
        return null;
    }

    public void setPassword(String password) {

    }

    public String getPassword() {
        return null;
    }

    public void setUserName(String userName) {

    }

    public String getUserName() {
        return null;
    }
}
