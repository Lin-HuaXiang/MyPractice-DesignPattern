package com.example.designpatternspider;

import java.math.BigDecimal;
import java.util.List;

import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMock;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMockMacd;
import com.example.designpatternspider.selenium.huobi.mock.ReviewDataMockRsi;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;
import com.example.designpatternspider.selenium.huobi.util.ReviewDataUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ReviewDataMockTests {

    @Test
    public void printData() throws Exception {
        String period = "1min";
        List<ReviewExport> listData = ReviewDataUtil.getLocalData(period);
        log.info("{}", listData.subList(listData.size() - 10, listData.size()));
    }

    @Test
    public void calcData() throws Exception {
        String period = "4hour";
        List<ReviewExport> listData = ReviewDataUtil.getLocalData(period);
        ReviewDataMock reviewDataMock = new ReviewDataMock(BigDecimal.valueOf(15), new ReviewDataMockMacd());
        reviewDataMock.printResultMock(listData);
    }

}
