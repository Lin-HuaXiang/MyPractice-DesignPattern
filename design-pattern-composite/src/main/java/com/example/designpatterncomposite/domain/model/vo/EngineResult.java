package com.example.designpatterncomposite.domain.model.vo;

import lombok.Data;

@Data
public class EngineResult {

    private boolean isSuccess;

    private String userId;

    private Long treeId;

    private Long nodeId;

    private String nodeValue;

    public EngineResult() {

    }

    public EngineResult(String userId, Long treeId, Long nodeId, String nodeValue) {
        this.isSuccess = true;
        this.userId = userId;
        this.treeId = treeId;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }

   
}
