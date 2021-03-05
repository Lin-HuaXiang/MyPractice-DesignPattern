package com.example.designpatternadaptor.mq;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccount {
    
    private String number;
    private String address;
    private Date accountDate;
    private String desc;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
