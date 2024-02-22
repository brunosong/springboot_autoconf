package com.brunosong.config.autoconfig;

import com.brunosong.config.BrunoAutoConfiguration;
import com.brunosong.config.BrunoConditionalOnClass;
import com.brunosong.config.EnableBrunoConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@BrunoAutoConfiguration
@BrunoConditionalOnClass("org.apache.catalina.startup.Tomcat")
@EnableBrunoConfigurationProperties(classes = ServerProperties.class)
public class TomcatWebServerConfig {

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory( ServerProperties serverProperties ) {

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setContextPath(serverProperties.getContextPath());
        serverFactory.setPort(serverProperties.getPort());

        return serverFactory;
    }

}
