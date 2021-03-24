package com.example.designpatternspider.selenium.huobi.po;

import java.util.List;
import lombok.ToString;

/**
 * Auto-generated: 2021-03-24 9:15:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ToString
public class HuoBiKline {

    private String ch;
    private String status;
    private long ts;
    private List<Data> data;

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getCh() {
        return ch;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getTs() {
        return ts;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

}