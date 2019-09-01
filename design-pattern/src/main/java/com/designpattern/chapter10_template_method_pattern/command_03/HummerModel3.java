package com.designpattern.chapter10_template_method_pattern.command_03;

public abstract class HummerModel3 {
    /**
     * 能发动，还要能停下来，那才是真本事
     */
    protected abstract void start();

    // 喇叭会出声音，是滴滴叫，还是哔哔叫
    protected abstract void stop();

    // 引擎会轰隆隆的作响，不想那是假的
    protected abstract void alarm();

    // 那模型应该会跑吧，别管是人推的，还是电力驱动的，总之要会跑。
    protected abstract void engineBoom();

    final public void run() {
        // 先发动汽车
        this.start();
        // 引擎开始轰鸣
        this.engineBoom();
        // 要让他叫的就是就叫，喇叭不想让它响它就不响
        if (this.isAlarm()) {
            this.alarm();
        }
        this.stop();
    }

    // 钩子方法，默认喇叭是会响的，
    // 由子类的一个方法返回值决定公共部分的执行结果
    protected boolean isAlarm() {
        return true;
    }



}
