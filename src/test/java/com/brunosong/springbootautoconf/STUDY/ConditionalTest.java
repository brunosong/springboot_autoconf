package com.brunosong.springbootautoconf.STUDY;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {


    @Test
    void condition(){

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


    @Configuration
    @Conditional(BrunoTestTrueCondition.class)
    static class Config1 {

        @Bean
        StudyBean studyBean(){
            return new StudyBean();
        }

    }


    @Configuration
    @Conditional(BrunoTestFalseCondition.class)
    static class Config2 {
        @Bean
        StudyBean studyBean(){
            return new StudyBean();
        }

    }


    static class BrunoTestTrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }


    static class BrunoTestFalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }




}
