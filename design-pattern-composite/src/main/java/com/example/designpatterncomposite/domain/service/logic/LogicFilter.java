package com.example.designpatterncomposite.domain.service.logic;

import java.util.List;
import java.util.Map;

import com.example.designpatterncomposite.domain.model.vo.TreeNodeLink;

public interface LogicFilter {

    /**
     * 逻辑决策器
     * 
     * @param matterValue      决策值
     * @param treeNodeLinkList 决策节点
     * @return 下一个节点id
     */
    Long filter(String matterValue, List<TreeNodeLink> treeNodeLinkList);

    /**
     * 获取决策值
     * 
     * @param treeId         决策物料
     * @param userId
     * @param decisionMatter
     * @return 决策值
     */
    String matterValue(Long treeId, String userId, Map<String, String> decisionMatter);
}
