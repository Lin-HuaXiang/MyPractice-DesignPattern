package com.example.designpatternvisitor.visitor.impl;

import com.example.designpatternvisitor.user.impl.Student;
import com.example.designpatternvisitor.user.impl.Teacher;
import com.example.designpatternvisitor.visitor.Visitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parent implements Visitor {

    @Override
    public void visit(Student student) {
        log.info("学生信息 姓名: {}, 班级: {}, 排名: {}", student.getName(), student.getClazz(), student.ranking());
    }

    @Override
    public void visit(Teacher teacher) {
        log.info("老师信息 姓名：{} 班级：{} 级别：{}", teacher.getName(), teacher.getClazz(), teacher.getIdentity());
    }





    
}
