package com.brunosong.config;

import com.brunosong.config.autoconfig.DispatcherServletConfig;
import com.brunosong.config.autoconfig.ServletWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Import({DispatcherServletConfig.class, ServletWebServerConfig.class})
public @interface EnableMyAutoConfiguration {
}
