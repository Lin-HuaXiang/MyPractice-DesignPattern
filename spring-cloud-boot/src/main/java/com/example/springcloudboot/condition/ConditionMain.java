package com.example.springcloudboot.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        BeanClass beanClass = context.getBean(BeanClass.class);
        System.out.println(beanClass);
    }
    
}
