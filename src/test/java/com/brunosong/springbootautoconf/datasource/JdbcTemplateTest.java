package com.brunosong.springbootautoconf.datasource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    void test(){

        jdbcTemplate.update("insert into bruno values ('KIM',  1)");
        jdbcTemplate.update("insert into bruno values ('SONG', 2)");

        Long count = jdbcTemplate.queryForObject("select count(*) from bruno", Long.class);
        Assertions.assertThat(count).isEqualTo(2L);

    }

}
