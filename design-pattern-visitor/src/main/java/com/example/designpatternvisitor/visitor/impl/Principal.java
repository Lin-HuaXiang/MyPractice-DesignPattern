package com.example.designpatternvisitor.visitor.impl;

import com.example.designpatternvisitor.user.impl.Student;
import com.example.designpatternvisitor.user.impl.Teacher;
import com.example.designpatternvisitor.visitor.Visitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Principal implements Visitor {

    @Override
    public void visit(Student student) {
        log.info("学生信息 姓名: {}, 班级: {}", student.getName(), student.getClazz());
    }

    @Override
    public void visit(Teacher teacher) {
        log.info("学生信息 姓名: {}, 班级: {} 升学率: {}", teacher.getName(), teacher.getClazz(), teacher.entranceRatio());
    }

}
