package com.example.designpatterncomposite.domain.service.engine;

import java.util.Map;

import com.example.designpatterncomposite.domain.model.aggregates.TreeRich;
import com.example.designpatterncomposite.domain.model.vo.TreeNode;
import com.example.designpatterncomposite.domain.model.vo.TreeRoot;
import com.example.designpatterncomposite.domain.service.logic.LogicFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EngineBase extends EngineConfig implements IEngine {

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    protected TreeNode enginDecisionMaker(TreeRich treeRich, Long treeId, String userId,
            Map<String, String> decisionMatter) {
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        // 规则树根id
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNodeInfo = treeNodeMap.get(rootNodeId);
        // 节点类型（NodeType）：1.子叶 2.果实
        while (treeNodeInfo.getNodeType().equals(1)) {
            String ruleKey = treeNodeInfo.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(treeId, userId, decisionMatter);
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLinkList());
            treeNodeInfo = treeNodeMap.get(nextNode);
            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(),
                    userId, treeId, treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNodeInfo;
    }

}
