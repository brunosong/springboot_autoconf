package com.brunosong.springbootautoconf.repository;

import com.brunosong.springbootautoconf.datasource.BrunoTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@BrunoTest
class HelloRepositoryJdbcTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HelloRepository helloRepository;

    @Test
    void findHelloFailed(){
        Assertions.assertThat(helloRepository.findHello("SONG")).isNull();
    }

    @Test
    void helloIncreaseTest() {

        Assertions.assertThat(helloRepository.countOf("SONG")).isEqualTo(0);

        helloRepository.increaseCount("SONG");
        Assertions.assertThat(helloRepository.countOf("SONG")).isEqualTo(1);

        helloRepository.increaseCount("SONG");
        Assertions.assertThat(helloRepository.countOf("SONG")).isEqualTo(2);

    }


}