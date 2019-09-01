package com.designpattern.chapter01_single_responsibility_principle.biz;

public interface IUserManager {

    void changeUserName(String newUserName);

    void changeHomeAddress(String newHomeAddress);

    void changeOfficeTel(String telNumber);
}
