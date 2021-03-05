package com.example.designpatterncomposite.domain.service.logic.impl;

import java.util.Map;

import com.example.designpatterncomposite.domain.service.logic.BaseLogic;

public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("age");
    }


    
}
