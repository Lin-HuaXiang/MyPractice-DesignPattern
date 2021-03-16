package com.example.designpatternmediator.mediator;

import java.util.Map;

import lombok.Data;

@Data
public class XNode {

    private String namespace;

    private String id;

    private String parameterType;

    private String resultType;

    private String sql;

    private Map<Integer, String> parameter;
    
}
