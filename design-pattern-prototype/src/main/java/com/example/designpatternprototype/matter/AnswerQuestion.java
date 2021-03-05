package com.example.designpatternprototype.matter;

import lombok.Data;

@Data
public class AnswerQuestion {

    private String name;

    private String key;

    public AnswerQuestion() {

    }

    public AnswerQuestion(String name, String key) {
        this.name = name;
        this.key = key;
    }
    
}
