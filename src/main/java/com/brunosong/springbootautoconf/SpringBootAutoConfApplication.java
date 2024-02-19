package com.brunosong.springbootautoconf;

import com.brunosong.springbootautoconf.controller.MemberController;
import com.brunosong.springbootautoconf.controller.OrderController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;


public class SpringBootAutoConfApplication {

    /* servletContext.addServlet : 서블릿을 등록 해준다. web.xml의 <servlet-mapping><servlet-name>hello</..></..>  */
    /* servletContext.addServlet.addMapping : 서블릿의 호출 경로를 맵핑해준다. web.xml의 <servlet-mapping><url-pattern>/hello</..></..>  */

    public static void main(String[] args) {

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        MemberController memberController = new MemberController();
        OrderController orderController = new OrderController();

        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            /* 프론트 컨트롤러를 적용해보자 */
            servletContext.addServlet("frontController", new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                            /* 모든 요청을 다 받기 때문에 각각 처리를 해줘야 한다. */
                            String requestURI = req.getRequestURI();

                            if(requestURI.equals("/memberGetId") && req.getMethod().equals(HttpMethod.GET.name())) {
                                String userName = req.getParameter("userName");
                                String id = memberController.getId(userName);

                                resp.setStatus(HttpStatus.OK.value());
                                resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                PrintWriter writer = resp.getWriter();
                                writer.println(id);

                            } else if(requestURI.equals("/order") && req.getMethod().equals(HttpMethod.POST.name())) {

                                String userName = req.getParameter("userName");
                                String order = orderController.order(userName);

                                resp.setStatus(HttpStatus.OK.value());
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
