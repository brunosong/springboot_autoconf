package com.brunosong.springbootautoconf.datasource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@BrunoTest
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
        jdbcTemplate.execute("create table IF NOT EXISTS bruno ( id int , username  varchar )");
    }

    @Test
    void test(){

        jdbcTemplate.update("insert into bruno values (1, 'KIM')");
        jdbcTemplate.update("insert into bruno values (2, 'SONG')");

        Long count = jdbcTemplate.queryForObject("select count(*) from bruno", Long.class);
        Assertions.assertThat(count).isEqualTo(2L);

    }

}
