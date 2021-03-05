package com.example.designpatternbuilder.design;

import java.math.BigDecimal;

public class MarcoPoloTile implements Matter {

    public String scene() {
        return "地砖";
    }

    public String brand() {
        return "⻢马可波罗(MARCO POLO)";
    }

    public String model() {
        return "缺省";
    }

    public BigDecimal price() {
        return new BigDecimal(140);
    }

    public String desc() {
        return "“⻢马可波罗”品牌诞⽣生于1996年年，作为国内最早品牌化的建陶品牌，以“⽂文化陶瓷”占领市场，享有“仿古砖⾄至尊”的美誉。";
    }

}
