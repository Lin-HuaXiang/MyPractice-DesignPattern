package com.example.designpatternbuilder.design;

import java.math.BigDecimal;

public class LevelTwoCeiling implements Matter {

    public String scene() {
        return "吊顶";
    }

    public String brand() {
        return "装修公司⾃自带";
    }

    public String model() {
        return "⼆二级顶";
    }

    public BigDecimal price() {
        return new BigDecimal(850);
    }

    public String desc() {
        return "两个层次的吊顶，⼆二级吊顶⾼高度⼀一般就往下吊20cm，要是层⾼高很⾼高，也可增加每级的厚度";
    }
}
