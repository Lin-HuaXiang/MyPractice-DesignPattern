package com.example.designpatterncomposite.domain.model.aggregates;

import java.util.Map;

import com.example.designpatterncomposite.domain.model.vo.TreeNode;
import com.example.designpatterncomposite.domain.model.vo.TreeRoot;

import lombok.Data;

@Data
public class TreeRich {

    private TreeRoot treeRoot;

    private Map<Long, TreeNode> treeNodeMap;

    public TreeRich(TreeRoot treeRoot, Map<Long, TreeNode> treeNodeMap) {
        this.treeRoot = treeRoot;
        this.treeNodeMap = treeNodeMap;
    }

    
}
