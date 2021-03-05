package com.example.designpatternbuilder.design;

import java.math.BigDecimal;

public class DuluxCoat implements Matter {

    public String scene() {
        return "涂料料";
    }

    public String brand() {
        return "多乐⼠士(Dulux)";
    }

    public String model() {
        return "第⼆二代";
    }

    public BigDecimal price() {
        return new BigDecimal(719);
    }

    public String desc() {
        return "多乐⼠士是阿克苏诺⻉贝尔旗下的著名建筑装饰油漆品牌，产品畅销于全球100个国家，每年年全球有5000万户家庭使⽤用多乐⼠士油漆。";
    }

}
