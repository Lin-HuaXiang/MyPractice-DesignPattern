package com.example.designpatternvisitor.visitor;

import com.example.designpatternvisitor.user.impl.Student;
import com.example.designpatternvisitor.user.impl.Teacher;

public interface Visitor {

    void visit(Student student);

    void visit(Teacher teacher);
    
}
