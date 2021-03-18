package com.example.designpatternstate.event;

import com.example.designpatternstate.Result;
import com.example.designpatternstate.State;
import com.example.designpatternstate.matter.ActivityService;
import com.example.designpatternstate.matter.Status;


public class CloseState extends State {

    @Override
    public Result arraignment(String activityId, Enum<com.example.designpatternstate.matter.Status> currentStatus) {
        return new Result("0001", "活动关闭不可提审");
    }

    @Override
    public Result checkPass(String activity, Enum<com.example.designpatternstate.matter.Status> currentStatus) {
        return new Result("0001", "活动关闭不可审核通过");
    }

    @Override
    public Result checkRefuse(String activityId, Enum<com.example.designpatternstate.matter.Status> currentStatus) {
        return new Result("0001", "活动关闭不可审核拒绝");
    }

    @Override
    public Result checkRevoke(String activityId, Enum<com.example.designpatternstate.matter.Status> currentStatus) {
        return new Result("0001", "活动关闭不可撤销审核");
    }

    @Override
    public Result close(String activityId, Enum<com.example.designpatternstate.matter.Status> currentStatus) {
        return new Result("0001", "活动关闭不可重复关闭");
    }

    @Override
    public Result open(String activityId, Enum<com.example.designpatternstate.matter.Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.Open);
        return new Result("0000", "活动开启完成");
    }

    @Override
    public Result doing(String activityId, Enum<com.example.designpatternstate.matter.Status> currentStatus) {
        return new Result("0001", "活动关闭不可变更活动中");
    }

    
}
