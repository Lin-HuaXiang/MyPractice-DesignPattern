package com.example.designpatternprototype;

import java.util.HashMap;
import java.util.Map;

import com.example.designpatternprototype.matter.AnswerQuestion;
import com.example.designpatternprototype.matter.ChoiceQuestion;

public class QuestionBankControllerNew {

    private QuestionBank questionBank = new QuestionBank();

    public QuestionBankControllerNew() {
        Map<String, String> map01 = new HashMap<>();
        map01.put("A", "JAVA2 EE");
        map01.put("B", "JAVA2 Card");
        map01.put("C", "JAVA2 ME");
        map01.put("D", "JAVA2 HE");
        map01.put("E", "JAVA2 SE");
        Map<String, String> map02 = new HashMap<>();
        map02.put("A", "JAVA程序的main⽅方法必须写在类⾥里里⾯面");
        map02.put("B", "JAVA程序中可以有多个main⽅方法");
        map02.put("C", "JAVA程序中类名必须与⽂文件名⼀一样");
        map02.put("D", "JAVA程序的main⽅方法中如果只有⼀一条语句句，可以不不⽤用{}(⼤大括号)括起来");
        Map<String, String> map03 = new HashMap<>();
        map03.put("A", "变量量由字⺟母、下划线、数字、$符号随意组成；");
        map03.put("B", "变量量不不能以数字作为开头；");
        map03.put("C", "A和a在java中是同⼀一个变量量；");
        map03.put("D", "不不同类型的变量量，可以起相同的名字；");
        Map<String, String> map04 = new HashMap<>();
        map04.put("A", "STRING");
        map04.put("B", "x3x;");
        map04.put("C", "void");
        map04.put("D", "de$f");
        Map<String, String> map05 = new HashMap<>();
        map05.put("A", "31");
        map05.put("B", "0");
        map05.put("C", "1");
        map05.put("D", "2");
        questionBank.append(new ChoiceQuestion("JAVA所定义的版本中不不包括", map01, "D"))
                .append(new ChoiceQuestion("下列列说法正确的是", map02, "A"))
                .append(new ChoiceQuestion("变量量命名规范说法正确的是", map03, "B"))
                .append(new ChoiceQuestion("以下()不不是合法的标识符", map04, "C"))
                .append(new ChoiceQuestion("表达式(11+3*8)/4%3的值是", map05, "D"))
                .append(new AnswerQuestion("⼩小红⻢马和⼩小⿊黑⻢马⽣生的⼩小⻢马⼏几条腿", "4条腿"))
                .append(new AnswerQuestion("铁棒打头疼还是⽊木棒打头疼", "头最疼"))
                .append(new AnswerQuestion("什什么床不不能睡觉", "⽛牙床"))
                .append(new AnswerQuestion("为什什么好⻢马不不吃回头草", "后⾯面的草没了了"));
    }

    public String createPager(String candidate, String number) throws CloneNotSupportedException {
        QuestionBank questionBankClone = (QuestionBank) questionBank.clone();
        questionBankClone.setCandidate(candidate);
        questionBankClone.setNumber(number);
        return questionBankClone.toString();
    }

}
