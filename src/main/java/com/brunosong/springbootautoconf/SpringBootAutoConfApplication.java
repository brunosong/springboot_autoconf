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

import java.io.IOException;
import java.io.PrintWriter;


public class SpringBootAutoConfApplication {

    public static void main(String[] args) {

        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean( "memberController", MemberController.class );
        applicationContext.registerBean( "orderController", OrderController.class );
        applicationContext.refresh();

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            /* 프론트 컨트롤러를 적용해보자 */
            servletContext.addServlet("frontController", new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                            /* 모든 요청을 다 받기 때문에 각각 처리를 해줘야 한다. */
                            String requestURI = req.getRequestURI();

                            if(requestURI.equals("/memberGetId") && req.getMethod().equals(HttpMethod.GET.name())) {
                                String userName = req.getParameter("userName");

                                MemberController memberController = applicationContext.getBean(MemberController.class);
                                String id = memberController.getId(userName);

                                resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                PrintWriter writer = resp.getWriter();
                                writer.println(id);

                            } else if(requestURI.equals("/order") && req.getMethod().equals(HttpMethod.POST.name())) {

                                String userName = req.getParameter("userName");
                                OrderController orderController = applicationContext.getBean(OrderController.class);
                                String order = orderController.order(userName);

                                // 톰캣(servlet container)에서 아무것도 없으면 (에러가 없으면) 200으로 자동으로 처리 해준다
                                resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                PrintWriter writer = resp.getWriter();
                                writer.println(order);

                            } else {
                                resp.setStatus(HttpStatus.NOT_FOUND.value());
                            }
                        }
                    }
            ).addMapping("/*");
        });

        webServer.start();

    }

}
