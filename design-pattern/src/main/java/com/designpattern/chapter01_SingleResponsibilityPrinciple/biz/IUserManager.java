package com.designpattern.chapter01_SingleResponsibilityPrinciple.biz;

public interface IUserManager {

    void changeUserName(String newUserName);

    void changeHomeAddress(String newHomeAddress);

    void changeOfficeTel(String telNumber);
}
