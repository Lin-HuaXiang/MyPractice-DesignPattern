package com.example.designpatternprototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.example.designpatternprototype.matter.AnswerQuestion;
import com.example.designpatternprototype.matter.ChoiceQuestion;
import com.example.designpatternprototype.util.Topic;
import com.example.designpatternprototype.util.TopicRandomUtil;

public class QuestionBank implements Cloneable {

    private String candidate;
    private String number;

    private ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<>();
    private ArrayList<AnswerQuestion> answerQuestionList = new ArrayList<>();

    public QuestionBank append(ChoiceQuestion choiceQuestion) {
        choiceQuestionList.add(choiceQuestion);
        return this;
    }

    public QuestionBank append(AnswerQuestion answerQuestion) {
        answerQuestionList.add(answerQuestion);
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        QuestionBank questionBank = (QuestionBank) super.clone();
        questionBank.choiceQuestionList = (ArrayList<ChoiceQuestion>) choiceQuestionList.clone();
        questionBank.answerQuestionList = (ArrayList<AnswerQuestion>) answerQuestionList.clone();

        // 打乱题目
        Collections.shuffle(questionBank.choiceQuestionList);
        Collections.shuffle(questionBank.answerQuestionList);
        // 打乱答案
        for (ChoiceQuestion question : questionBank.choiceQuestionList) {
            Topic random = TopicRandomUtil.random(question.getOption(), question.getKey());
            question.setOption(random.getOption());
            question.setKey(random.getKey());
        }
        return questionBank;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder detail = new StringBuilder("考⽣生：" + candidate + "\r\n" + "考号：" + number + "\r\n"
                + "--------------------------------------------\r\n" + "⼀一、选择题" + "\r\n\n");
        for (int idx = 0; idx < choiceQuestionList.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(choiceQuestionList.get(idx).getName()).append("\r\n");
            Map<String, String> option = choiceQuestionList.get(idx).getOption();
            for (String key : option.keySet()) {
                detail.append(key).append("：").append(option.get(key)).append("\r\n");
            }
            detail.append("答案：").append(choiceQuestionList.get(idx).getKey()).append("\r\n\n");
        }
        detail.append("二、问答题" + "\r\n\n");
        for (int idx = 0; idx < answerQuestionList.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(answerQuestionList.get(idx).getName()).append("\r\n");
            detail.append("答案：").append(answerQuestionList.get(idx).getKey()).append("\r\n\n");
        }
        return detail.toString();
    }

}
