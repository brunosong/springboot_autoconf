package com.brunosong.springbootautoconf.userconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatServerConfig {

    // 유저 구성정보에서 인프라스트럭처 빈을 등록을 하게 된다면 @ConditionalOnMissingBean 에 의해서 유저 구성정보를 우선으로 사용한다.
    // 그 이유는 @ConditionalOnMissingBean 이  ServletWebServerFactory 와 같은 타입이 존재한다면 그 빈을 만들지 않기 때문이다.
    // 이 설정을 없애면 autoconfig에 있는 톰캣이 사용되고 이 설정을 만들어 두면 포트가 9090의 설정정보를 가진 현재 톰캣이 실행된다.
    @Bean("tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {

        TomcatServletWebServerFactory server = new TomcatServletWebServerFactory();
        server.setPort(9090);
        return server;

    }


}
