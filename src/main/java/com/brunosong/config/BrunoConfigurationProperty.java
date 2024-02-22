package com.brunosong.config;


import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 이 어노테이션이 붙은 것은 마킹하는 역활로써 프로퍼티로 사용되는 클래스란것을 알려준다. */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface BrunoConfigurationProperty {
    String prefix() default "";
}
