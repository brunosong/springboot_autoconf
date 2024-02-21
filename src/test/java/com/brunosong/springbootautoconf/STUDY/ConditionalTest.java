package com.brunosong.springbootautoconf.STUDY;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {


    @Test
    void condition(){

        AnnotationConfigApplicationContext ac1 = new AnnotationConfigApplicationContext();
        ac1.register(Config1.class);
        ac1.refresh();

        StudyBean studyBean1 = ac1.getBean("studyBean", StudyBean.class);
        Assertions.assertThat(studyBean1).isNotNull();


        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
        ac2.register(Config2.class);
        ac2.refresh();

        Assertions.assertThatThrownBy(() -> {
            ac2.getBean("studyBean", StudyBean.class);
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
