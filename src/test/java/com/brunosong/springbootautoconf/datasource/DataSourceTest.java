package com.brunosong.springbootautoconf.datasource;

import com.brunosong.springbootautoconf.SpringBootAutoConfApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
/* 모든 구성 정보를 끌어오는 시작점이 되는 클래스 */
@ContextConfiguration(classes = SpringBootAutoConfApplication.class)
@TestPropertySource("classpath:/application.properties")
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
