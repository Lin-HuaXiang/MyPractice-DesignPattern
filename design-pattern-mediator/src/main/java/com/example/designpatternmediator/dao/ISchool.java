package com.example.designpatternmediator.dao;

import com.example.designpatternmediator.po.School;

public interface ISchool {
    
    School querySchoolInfoById(Long treeId);
}
