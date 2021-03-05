package com.example.designpatternadaptor.design;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RebateInfo {

    private String userId;

    private String bizId;

    private String bizTime;

    private String desc;
    
}
