package com.brunosong.springbootautoconf.datasource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@BrunoTest
public class DataSourceTest {

    /* application.properties 는 스프링프레임워크의 기본 동작 방식은 아니고 스프링 부트에서 지원을 해주는 동작이다. */
    /* gredle과 maven에 runtimeOnly는 프로젝트에서 직접적으로 그 라이브러리르 사용할 일이 없을때 (코드에서 불러다 쓰는) 사용해준다. */
    @Autowired
    DataSource dataSource;

    @Test
    void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
