package com.brunosong.springbootautoconf;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;


@MySpringBootAnnotation
public class SpringBootAutoConfApplication {

    public static void main(String[] args) {
        MySpringBootApplication.run(SpringBootAutoConfApplication.class, args);
    }


}