package com.brunosong.springbootautoconf.controller;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MemberApiTest {

    @Test
    void memberControllerTest() {

        TestRestTemplate restTemplate = new TestRestTemplate();
        // ResponseEntity 웹 리스폰스에 모든 요소를 담고 있는 객체
        ResponseEntity<String> res =
                restTemplate.getForEntity("http://localhost:9090/app/member/getId?userName={userName}", String.class, "BrunoSong");

        //바디 부분이 String 이다.
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getBody()).startsWith("BrunoSong");
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);

    }
}
