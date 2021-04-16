package com.example.springcloudboot.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(GpCondition.class)
@ConditionalOnProperty(value = "gp.bean.enable", havingValue = "true", matchIfMissing = true)
@ConditionalOnResource(resources = "/gp.properties")
public class ConditionConfig {

    @Bean
    @Conditional(GpCondition.class)
    public BeanClass beanClass() {
        return new BeanClass();
    }
    
}
