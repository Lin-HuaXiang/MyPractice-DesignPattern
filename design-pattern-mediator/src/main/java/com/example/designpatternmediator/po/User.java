package com.example.designpatternmediator.po;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    
    private Long id;
    private String name;
    private Integer age;
    private Date createTime;
    private Date updateTime;
}
