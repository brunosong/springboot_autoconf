package com.brunosong.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

public class BrunoConditionOnClass implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes =
                metadata.getAnnotationAttributes(BrunoConditionalOnClass.class.getName());

        return ClassUtils.isPresent((String)annotationAttributes.get("value"),context.getClassLoader());
    }
}
