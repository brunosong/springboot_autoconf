package com.brunosong.springbootautoconf;

import com.brunosong.config.MySpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@MySpringBootApplication
public class SpringBootAutoConfApplication {

    private final JdbcTemplate jdbcTemplate;

    public SpringBootAutoConfApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("create table IF NOT EXISTS bruno ( name  varchar, count int )");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutoConfApplication.class, args);
    }

}