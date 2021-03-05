package com.example.designpatterncomposite.domain.model.vo;

import lombok.Data;

@Data
public class TreeNodeLink {

    private Long nodeIdFrom;

    private Long nodeIdTo;

    private Integer ruleLimitType;

    private String ruleLimitValue;

  

}
