package com.example.designpatternspider.selenium.huobi.po.export;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewExport {

    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "time")
    private String time;
    @ExcelProperty(value = "price")
    private BigDecimal price;
    @ExcelProperty(value = "open")
    private BigDecimal open;
    @ExcelProperty(value = "close")
    private BigDecimal close;
    @ExcelProperty(value = "low")
    private BigDecimal low;
    @ExcelProperty(value = "high")
    private BigDecimal high;
    @ExcelProperty(value = "macd")
    private BigDecimal macd;
    @ExcelProperty(value = "dif")
    private BigDecimal dif;
    @ExcelProperty(value = "dea")
    private BigDecimal dea;
    @ExcelProperty(value = "k")
    private BigDecimal k;
    @ExcelProperty(value = "d")
    private BigDecimal d;
    @ExcelProperty(value = "j")
    private BigDecimal j;
    @ExcelProperty(value = "calcRsi9")
    private BigDecimal calcRsi9;
    @ExcelProperty(value = "calcRsi12")
    private BigDecimal calcRsi12;
    @ExcelProperty(value = "calcRsi14")
    private BigDecimal calcRsi14;
    @ExcelProperty(value = "calcRsi72")
    private BigDecimal calcRsi72;

    private List<ReviewExport> lowerData;
}
