package com.brunosong.springbootautoconf;

import com.brunosong.springbootautoconf.controller.MemberController;
import com.brunosong.springbootautoconf.controller.OrderController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class SpringBootAutoConfApplication {

    public static void main(String[] args) {

        /*
        * 이전 소스에서는 servlet 부분과 스프링컨테이너 부분이 따로 나뉘어 있었다
        * 이젠 스프링컨테이너안에 디스패쳐를 넣어줘서 스프링으로 통합해준다.
        *  */
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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

        applicationContext.registerBean( MemberController.class );
        applicationContext.registerBean( OrderController.class );
        applicationContext.refresh();


    }

}