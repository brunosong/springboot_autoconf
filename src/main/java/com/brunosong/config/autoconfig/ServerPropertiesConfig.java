package com.brunosong.config.autoconfig;

import com.brunosong.config.BrunoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@BrunoAutoConfiguration
public class ServerPropertiesConfig {

    @Bean
    ServerProperties serverProperties(Environment environment) {

        ServerProperties serverProperties = new ServerProperties();

        serverProperties.setPort(Integer.parseInt(environment.getProperty("port")));
        serverProperties.setContextPath(environment.getProperty("contextPath"));

        return serverProperties;
    }

}
