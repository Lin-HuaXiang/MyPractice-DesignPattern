package com.example.designpatternspider.selenium.huobi.po.export;

import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewExport {

    @ExcelProperty()
    private String time;
    @ExcelProperty()
    private BigDecimal price;
    @ExcelProperty()
    private BigDecimal macd;
    @ExcelProperty()
    private BigDecimal dif;
    @ExcelProperty()
    private BigDecimal dea;
    @ExcelProperty()
    private BigDecimal k;
    @ExcelProperty()
    private BigDecimal d;
    @ExcelProperty()
    private BigDecimal j;
    @ExcelProperty()
    private BigDecimal calcRsi9;
    @ExcelProperty()
    private BigDecimal calcRsi12;
    @ExcelProperty()
    private BigDecimal calcRsi14;
    @ExcelProperty()
    private BigDecimal calcRsi72;

}
