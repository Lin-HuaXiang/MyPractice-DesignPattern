package com.designpattern.chapter01_SingleResponsibilityPrinciple.biz;

import com.designpattern.chapter01_SingleResponsibilityPrinciple.bo.IUserBO;

public interface IUserBiz {

    boolean changePassword(String password);

    boolean deleteUser(IUserBO userBO);

    void mapUser(IUserBO userBO);

    boolean addOrg(IUserBO userBO, int orgID);

    boolean addRole(IUserBO userBO, int roleID);
}
