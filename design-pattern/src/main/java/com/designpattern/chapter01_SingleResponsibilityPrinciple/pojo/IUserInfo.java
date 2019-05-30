package com.designpattern.chapter01_SingleResponsibilityPrinciple.pojo;

import com.designpattern.chapter01_SingleResponsibilityPrinciple.biz.IUserBiz;
import com.designpattern.chapter01_SingleResponsibilityPrinciple.bo.IUserBO;

public interface IUserInfo extends IUserBO, IUserBiz {


}
