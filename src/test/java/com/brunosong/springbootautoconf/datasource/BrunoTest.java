package com.brunosong.springbootautoconf.datasource;

import com.brunosong.springbootautoconf.SpringBootAutoConfApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class)
/* 모든 구성 정보를 끌어오는 시작점이 되는 클래스 */
@ContextConfiguration(classes = SpringBootAutoConfApplication.class)
@TestPropertySource("classpath:/application.properties")
@Transactional
public @interface BrunoTest {
}
