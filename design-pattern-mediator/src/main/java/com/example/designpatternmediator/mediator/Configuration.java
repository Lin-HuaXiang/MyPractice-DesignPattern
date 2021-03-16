package com.example.designpatternmediator.mediator;

import java.sql.Connection;
import java.util.Map;

import lombok.Data;

@Data
public class Configuration {

    protected Connection connection;

    protected Map<String, String> dataSource;

    protected Map<String, XNode> mapperElement;
    
}
