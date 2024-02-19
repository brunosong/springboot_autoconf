package com.brunosong.springbootautoconf;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;


public class SpringBootAutoConfApplication {

    /* servletContext.addServlet : 서블릿을 등록 해준다. web.xml의 <servlet-mapping><servlet-name>hello</..></..>  */
    /* servletContext.addServlet.addMapping : 서블릿의 호출 경로를 맵핑해준다. web.xml의 <servlet-mapping><url-pattern>/hello</..></..>  */

    public static void main(String[] args) {

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            /* hello 서블릿을 만들어 /hello 요청으로 맵핑을 한다. */
            servletContext.addServlet("hello", new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                            String name = req.getParameter("name");

                            //웹 응답(response)의 3가지 요소를 갖춘다. (상태코드,헤더,바디)
                            resp.setStatus(HttpStatus.OK.value());
                            resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                            PrintWriter writer = resp.getWriter();
                            writer.println("Hello My name is " + name);
                        }
                    }
            ).addMapping("/hello");
        });

        webServer.start();

    }

}
