package com.example.designpatternfacade.web;

import com.example.designpatternfacade.annotation.DoDoor;
import com.example.designpatternfacade.domain.UserInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloWorldController {
    
    @DoDoor(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"⾮白名单可访问⽤用户拦截！\"}")
    @RequestMapping(value = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) {
        return new UserInfo("虫虫：" + userId, 19, "天津市南开区旮旯胡同100号");
    }

}
