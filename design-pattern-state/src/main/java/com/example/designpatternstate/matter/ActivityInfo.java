package com.example.designpatternstate.matter;

import java.util.Date;

import lombok.Data;

@Data
public class ActivityInfo {

    private String activityId;
    private String activityName;
    private Enum<Status> status;
    private Date beginTime;
    private Date endTime;
    
}
