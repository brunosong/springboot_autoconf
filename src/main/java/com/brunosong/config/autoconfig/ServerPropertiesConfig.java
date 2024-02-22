package com.brunosong.config.autoconfig;

import com.brunosong.config.BrunoAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@BrunoAutoConfiguration
public class ServerPropertiesConfig {

    @Bean
    ServerProperties serverProperties(Environment environment) {

        /* 일일이 하나하나 맵핑을 하려면 너무 힘들기 때문에 Binder를 사용하여 자동으로 맵핑을 하게 해준다. */
        return Binder.get(environment).bindOrCreate("", ServerProperties.class);
    }

}
