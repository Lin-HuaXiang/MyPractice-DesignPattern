package com.example.designpatternmediator.dao;

import com.example.designpatternmediator.po.User;

public interface IUserDao {
    
    User queryUserInfoById(Long id);
}
