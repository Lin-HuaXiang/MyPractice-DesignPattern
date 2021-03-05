package com.example.designpatternfacade.config;

import org.springframework.util.StringUtils;

public class StarterService {
    
    private String userStr;

    public StarterService(String userStr)  {
        this.userStr = userStr;
    }

    public String[] split(String separatorChar) {
        return StringUtils.split(this.userStr, separatorChar);
    }

}
