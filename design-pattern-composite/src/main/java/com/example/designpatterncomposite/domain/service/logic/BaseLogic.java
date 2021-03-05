package com.example.designpatterncomposite.domain.service.logic;

import java.util.List;

import com.example.designpatterncomposite.domain.model.vo.TreeNodeLink;

public abstract class BaseLogic implements LogicFilter {

    @Override
    public Long filter(String matterValue, List<TreeNodeLink> treeNodeLinkList) {
        for (TreeNodeLink nodeLink : treeNodeLinkList) {
            if (decisionLogic(matterValue, nodeLink)) {
                return nodeLink.getNodeIdTo();
            }
        }
        return null;
    }

    private boolean decisionLogic(String matterValue, TreeNodeLink nodeLink) {
        // RuleLimitType； 1等于 2大于 3小于 4大于等于 5小于等于
        switch (nodeLink.getRuleLimitType()) {
            case 1:
                return matterValue.equals(nodeLink.getRuleLimitValue());
            case 2:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLink.getRuleLimitValue());
            case 3:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLink.getRuleLimitValue());
            case 4:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLink.getRuleLimitValue());
            case 5:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLink.getRuleLimitValue());
            default:
                return false;
        }
    }

}
