package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.dto.topic.TopicRequestDto;
import com.backend.howaboutyou.dto.topic.TopicResponseDto;
import com.backend.howaboutyou.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TopicService topicService;

    @Autowired
    MockMvc mvc;

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void saveTopic() {
        TopicRequestDto topicRequestDto = new TopicRequestDto();
        topicRequestDto.setTitle("title");
        topicService.saveTopic(topicRequestDto);
    }

    @Test
    public void getTopicTest() {
        // given
        String url = "http://localhost:" + port + "/api/topic/get";

        // when
        TopicResponseDto topicResponseDto = restTemplate.getForObject(url, TopicResponseDto.class);

        // then
        System.out.println(topicResponseDto);

    }

}