package com.brunosong.springbootautoconf;

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

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }


    public static void main(String[] args) {

        /* 스프링에서 가장 많이 쓰는 Annotation으로 설정을 사용하겠다. */
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                /*
                * 이 부분이 없어도 되는 이유는 ApplicationContextAware setApplicationContext(ApplicationContext applicationContext) 을
                * DispatcherServlet이 상속을 받고 있기 때문에 스프링이 알아서 자동으로 주입시켜준다.
                * */
                //dispatcherServlet.setApplicationContext(this);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet )
                            .addMapping("/*");
                });

                webServer.start();
            }
        };

        applicationContext.register(SpringBootAutoConfApplication.class);
        applicationContext.refresh();

    }

}