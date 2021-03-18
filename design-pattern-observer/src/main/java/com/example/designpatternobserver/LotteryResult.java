package com.example.designpatternobserver;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LotteryResult {

    private String uId;

    private String msg;

    private Date dateTime;
    
    
}
