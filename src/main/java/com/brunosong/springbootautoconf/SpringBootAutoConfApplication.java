package com.brunosong.springbootautoconf;

import com.brunosong.springbootautoconf.controller.MemberController;
import com.brunosong.springbootautoconf.controller.OrderController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.io.PrintWriter;


public class SpringBootAutoConfApplication {

    public static void main(String[] args) {

        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean( "memberController", MemberController.class );
        applicationContext.registerBean( "orderController", OrderController.class );
        applicationContext.refresh();

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        /* 디스패쳐 서블릿에게 스프링 컨테이너를 주입시켜 준다. */
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet", new DispatcherServlet( applicationContext ) )
                    .addMapping("/*");
        });

        /*
        *  GenericApplicationContext -> GenericWebApplicationContext 으로 변경이 되면서
        *  dispatcherServlet을 찾는거는 가능해졌지만 컨트롤러 등록은 되어있지 않은 상태이다.
        *
        * */

        webServer.start();

    }

}
//
//
//new HttpServlet() {
//@Override
//protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        /* 모든 요청을 다 받기 때문에 각각 처리를 해줘야 한다. */
//        String requestURI = req.getRequestURI();
//
//        if(requestURI.equals("/memberGetId") && req.getMethod().equals(HttpMethod.GET.name())) {
//        String userName = req.getParameter("userName");
//
//        MemberController memberController = applicationContext.getBean(MemberController.class);
//        String id = memberController.getId(userName);
//
//        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//        PrintWriter writer = resp.getWriter();
//        writer.println(id);
//
//        } else if(requestURI.equals("/order") && req.getMethod().equals(HttpMethod.POST.name())) {
//
//        String userName = req.getParameter("userName");
//        OrderController orderController = applicationContext.getBean(OrderController.class);
//        String order = orderController.order(userName);
//
//        // 톰캣(servlet container)에서 아무것도 없으면 (에러가 없으면) 200으로 자동으로 처리 해준다
//        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//        PrintWriter writer = resp.getWriter();
//        writer.println(order);
//
//        } else {
//        resp.setStatus(HttpStatus.NOT_FOUND.value());
//        }
//        }
//        }
