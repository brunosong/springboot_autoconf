package com.brunosong.config.autoconfig;

import com.brunosong.config.BrunoAutoConfiguration;
import com.brunosong.config.BrunoConditionalOnClass;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@BrunoAutoConfiguration
@BrunoConditionalOnClass("org.eclipse.jetty.server.Server")
public class JettyWebServerConfig {
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }

}
