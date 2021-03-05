package com.example.designpatterncomposite.domain.model.vo;

import java.util.List;

import lombok.Data;

@Data
public class TreeNode {

    private Long treeId;

    private Long treeNodeId;

    private Integer nodeType;

    private String nodeValue;

    private String ruleKey;

    private String ruleDesc;
    
    private List<TreeNodeLink> treeNodeLinkList;

}
