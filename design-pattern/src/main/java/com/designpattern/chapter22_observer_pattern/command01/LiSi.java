package com.designpattern.chapter22_observer_pattern.command01;

public class LiSi implements ILiSi {

    @Override
    public void update(String context) {
        System.out.println("李斯：观察到韩非子活动，开始向老板汇报了。。。");
        this.reportToQiShiHuang(context);
        System.out.println("李斯：汇报完毕。。。");
    }

    private void reportToQiShiHuang(String reportContext) {
        System.out.println("李斯：报告，秦老板！韩非子有活动了---》" + reportContext);
    }
}

