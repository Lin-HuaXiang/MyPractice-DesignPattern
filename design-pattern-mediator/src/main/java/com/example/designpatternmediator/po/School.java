package com.example.designpatternmediator.po;

import java.util.Date;

import lombok.Data;

@Data
public class School {

    private Long id;
    private String name;
    private String address;
    private Date createTime;
    private Date updateTime;
    
}
