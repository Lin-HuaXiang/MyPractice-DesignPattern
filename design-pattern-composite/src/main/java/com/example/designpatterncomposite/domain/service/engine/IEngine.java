package com.example.designpatterncomposite.domain.service.engine;

import java.util.Map;

import com.example.designpatterncomposite.domain.model.aggregates.TreeRich;
import com.example.designpatterncomposite.domain.model.vo.EngineResult;

public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, Map<String, String> decisionMatter);
    
}
