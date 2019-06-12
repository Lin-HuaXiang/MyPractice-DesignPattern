package com.designpattern.chapter01_SingleResponsibilityPrinciple.biz;

public interface IUserBiz {

    boolean changePassword(String password);

    boolean deleteUser(IUserBO userBO);

    void mapUser(IUserBO userBO);

    boolean addOrg(IUserBO userBO, int orgID);

    boolean addRole(IUserBO userBO, int roleID);
}
