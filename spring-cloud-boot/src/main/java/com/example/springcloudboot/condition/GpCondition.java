package com.example.springcloudboot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class GpCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 此处进行条件判断，如果返回true，表示需要加载该配置类或者Bean
        // 否则，表示不加载
        String os = conditionContext.getEnvironment().getProperty("os.name");
        if (os.contains("Windows")) {
            return true;
        }
        return false;
    }


    
}
