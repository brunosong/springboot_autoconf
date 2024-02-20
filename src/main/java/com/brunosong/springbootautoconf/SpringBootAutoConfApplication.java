package com.brunosong.springbootautoconf;

import com.brunosong.springbootautoconf.controller.MemberController;
import com.brunosong.springbootautoconf.controller.OrderController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@Configuration
@ComponentScan
public class SpringBootAutoConfApplication {

    public static void main(String[] args) {

        /* 스프링에서 가장 많이 쓰는 Annotation으로 설정을 사용하겠다. */
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet( this ) )
                            .addMapping("/*");
                });

                webServer.start();
            }
        };

        //설정파일의 경로를 알려준다.
        applicationContext.register(SpringBootAutoConfApplication.class);
        applicationContext.refresh();

    }

}