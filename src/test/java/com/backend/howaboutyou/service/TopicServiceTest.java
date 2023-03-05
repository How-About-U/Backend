package com.backend.howaboutyou.service;

import com.backend.howaboutyou.dto.TopicRequestDto;
import com.backend.howaboutyou.dto.TopicResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;



@SpringBootTest
@Transactional
class TopicServiceTest {

    @Autowired
    TopicService topicService;

    @Test
    public void topicSave() {
        // given
        TopicRequestDto topicRequestDto = new TopicRequestDto();
        topicRequestDto.setTitle("title");

        // when
        Long savedTopicId = topicService.saveTopic(topicRequestDto);
        TopicResponseDto topic = topicService.getTodayTopic();

        System.out.println(topic);
    }
}