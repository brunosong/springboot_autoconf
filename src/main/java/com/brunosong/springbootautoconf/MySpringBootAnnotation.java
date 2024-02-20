package com.brunosong.springbootautoconf;


import com.brunosong.config.Config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})  // TYPE은 클래스, 인터페이스 , Enum 이다.
@Configuration
@ComponentScan
@Import(Config.class)
public @interface MySpringBootAnnotation {

}
