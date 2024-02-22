package com.brunosong.config.autoconfig;

import com.brunosong.config.BrunoConfigurationProperty;
import org.springframework.stereotype.Component;

/* @Component를 달고 있는 클래스들은 두가지 방식에 의해서 스프링에 빈으로 등록될 수 있다. @ComponentScan , @Import */

@BrunoConfigurationProperty
public class ServerProperties {

    private String contextPath;
    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
