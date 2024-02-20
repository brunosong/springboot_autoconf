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

        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean( MemberController.class );
        applicationContext.registerBean( OrderController.class );
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet", new DispatcherServlet( applicationContext ) )
                    .addMapping("/*");
        });

        webServer.start();

    }

}