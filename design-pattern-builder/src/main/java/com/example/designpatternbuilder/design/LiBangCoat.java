package com.example.designpatternbuilder.design;

import java.math.BigDecimal;

public class LiBangCoat implements Matter {

    public String scene() {
        return "涂料料";
    }

    public String brand() {
        return "⽴立邦";
    }

    public String model() {
        return "默认级别";
    }

    public BigDecimal price() {
        return new BigDecimal(650);
    }

    public String desc() {
        return "⽴立邦始终以开发绿⾊色产品、注重⾼高科技、⾼高品质为⽬目标，以技术⼒力力量量不不断推进科研和开发，满⾜足消费者需求。";
    }
}
