package com.example.designpatternmemo.design;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfigFile {
 
    private String versionNo;

    private String content;

    private Date dateTime;

    private String operator;

}
