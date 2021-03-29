package com.example.designpatternspider.selenium.huobi.util.excel;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.designpatternspider.selenium.huobi.po.export.ReviewExport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewExportListener extends AnalysisEventListener<ReviewExport> {


    private List<ReviewExport> list = new ArrayList<>();

    public List<ReviewExport> getList() {
        return list;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("All data parsed complete!");
    }

    @Override
    public void invoke(ReviewExport data, AnalysisContext context) {
        list.add(data);
    }
}