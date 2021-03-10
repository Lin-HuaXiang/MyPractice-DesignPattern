package com.example.designpatternproxy;

import com.example.designpatternproxy.agent.Select;

public interface IUserDao {

    @Select("select userName from user where id = #{uId}")
    String queryUserInfo(String uId);
    
}
