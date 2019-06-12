package com.designpattern.chapter01_SingleResponsibilityPrinciple.biz;

public interface IUserBO {

    void setUserID(String userID);

    String getUserID();

    void setPassword(String password);

    String getPassword();

    void setUserName(String userName);

    String getUserName();
}
