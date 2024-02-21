package com.brunosong.springbootautoconf.STUDY;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {


    @Test
    void conditionTest(){

        // 스프링 부트에서 제공하는 ApplicationContext 전용 테스트 객체
        // 빈을 가지고 있는지 테스트
        ApplicationContextRunner ar1 = new ApplicationContextRunner();
        ar1.withUserConfiguration(Config1.class)
                .run( context -> {
                    Assertions.assertThat(context).hasSingleBean(StudyBean.class);
                    Assertions.assertThat(context).hasSingleBean(Config1.class);
                });


        // 빈을 가지고 있지 않는지 테스트
        ApplicationContextRunner ar2 = new ApplicationContextRunner();
        ar2.withUserConfiguration(Config2.class)
                .run( context -> {
                    Assertions.assertThat(context).doesNotHaveBean(Config2.class);
                    Assertions.assertThat(context).doesNotHaveBean(StudyBean.class);

                });


    }

    static class StudyBean {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
    @Conditional(BooleanCondition.class)
    @interface BrunoTrueConditional {}


    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1 {

        @Bean
        StudyBean studyBean(){
            return new StudyBean();
        }

    }


    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        StudyBean studyBean(){
            return new StudyBean();
        }

    }


    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            Boolean value = (Boolean)annotationAttributes.get("value");
            return value;
        }
    }


}
