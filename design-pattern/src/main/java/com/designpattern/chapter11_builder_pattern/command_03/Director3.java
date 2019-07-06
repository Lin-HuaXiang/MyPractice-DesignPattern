package com.designpattern.chapter11_builder_pattern.command_03;

import com.designpattern.chapter11_builder_pattern.command_01.BMWModel1;
import com.designpattern.chapter11_builder_pattern.command_01.BenzModel1;
import com.designpattern.chapter11_builder_pattern.command_02.BMWBuilder2;
import com.designpattern.chapter11_builder_pattern.command_02.BenzBuilder2;

import java.util.ArrayList;

public class Director3 {

    private ArrayList<String> sequence = new ArrayList<>();
    private BenzBuilder2 benzBuilder2 = new BenzBuilder2();
    private BMWBuilder2 bmwBuilder2 = new BMWBuilder2();

    /**
     * A类型的奔驰车模型，先start，然后stop，其他什么引擎、喇叭一概没有
     * @return
     */
    public BenzModel1 getABenzModel() {
        // 清理场景，这里是一些初级程序员不注意的地方，只要一个对象，会被其他方法add影响
        this.sequence.clear();
        // ABenzModel的执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");
        // 按照顺序返回一个奔驰车
        this.benzBuilder2.setSequence(this.sequence);
        return (BenzModel1) this.benzBuilder2.getCarModel();
    }

    /**
     * B类型的奔驰车模型，先发动引擎，然后启动，然后停止，没有喇叭
     * @return
     */
    public BenzModel1 getBBenzModel() {
        this.sequence.clear();
        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder2.setSequence(this.sequence);
        return (BenzModel1) this.benzBuilder2.getCarModel();
    }

    /**
     * C型号的宝马车，是先按下喇叭，然后启动，然后停止
     */
    public BMWModel1 getCBMWModel() {
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder2.setSequence(this.sequence);
        return (BMWModel1) this.bmwBuilder2.getCarModel();
    }

    /**
     * D型号的宝马车，只有一个功能，就是跑，启动起来就跑，永不停止
     */
    public BMWModel1 getDBMWModel() {
        this.sequence.clear();
        this.sequence.add("start");
        this.bmwBuilder2.setSequence(this.sequence);
        return (BMWModel1) this.bmwBuilder2.getCarModel();
    }
}
