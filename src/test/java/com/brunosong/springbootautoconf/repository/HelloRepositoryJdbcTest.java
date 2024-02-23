package com.brunosong.springbootautoconf.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/* 리포지토리를 사용하는데 웹쪽은 사용하지 않겠다. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
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