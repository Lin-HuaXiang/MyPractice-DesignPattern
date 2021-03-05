package com.example.designpatternprototype.util;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    Map<String, String> option;

    String key;
    
}
