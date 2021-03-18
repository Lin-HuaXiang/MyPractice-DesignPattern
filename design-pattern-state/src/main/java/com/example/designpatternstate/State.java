package com.example.designpatternstate;

import com.example.designpatternstate.matter.Status;

public abstract class State {

    public abstract Result arraignment(String activityId, Enum<Status> currentStatus);

    public abstract Result checkPass(String activity, Enum<Status> currentStatus);

    public abstract Result checkRefuse(String activityId, Enum<Status> currentStatus);

    public abstract Result checkRevoke(String activityId, Enum<Status> currentStatus);

    public abstract Result close(String activityId, Enum<Status> currentStatus);

    public abstract Result open(String activityId, Enum<Status> currentStatus);

    public abstract Result doing(String activityId, Enum<Status> currentStatus);
}
