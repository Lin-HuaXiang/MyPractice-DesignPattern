package com.example.designpatternprototype.matter;

import java.util.Map;

import lombok.Data;


@Data
public class ChoiceQuestion {

    private String name;

    private Map<String, String> option;
    
    private String key;

    public ChoiceQuestion() {}

    public ChoiceQuestion(String name, Map<String, String> option, String key) {
        this.name = name;
        this.option = option;
        this.key = key;
    }

}
