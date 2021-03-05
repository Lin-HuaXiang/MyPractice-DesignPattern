package com.example.designpatternflyweight;

import java.util.Date;

import lombok.Data;

@Data
public class Activity {

    private Long id;

    private String name;

    private String desc;

    private Date startTime;

    private Date stopTime;

    private Stock stock;
    
}
