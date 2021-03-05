package com.example.designpatterncomposite.domain.service.engine.impl;

import java.util.Map;

import com.example.designpatterncomposite.domain.model.aggregates.TreeRich;
import com.example.designpatterncomposite.domain.model.vo.EngineResult;
import com.example.designpatterncomposite.domain.model.vo.TreeNode;
import com.example.designpatterncomposite.domain.service.engine.EngineBase;

public class TreeEngineHandle extends EngineBase {

    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter) {
        // 决策流程
        TreeNode treeNode = enginDecisionMaker(treeRich, treeId, userId, decisionMatter);
        // 决策结果
        return new EngineResult(userId, treeId, treeNode.getTreeNodeId(), treeNode.getNodeValue());
    }
}
