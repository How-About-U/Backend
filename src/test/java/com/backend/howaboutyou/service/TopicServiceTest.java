package com.backend.howaboutyou.service;

import com.backend.howaboutyou.domain.Topic;
import com.backend.howaboutyou.dto.topic.TopicRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TopicServiceTest {

    @Autowired
    private TopicService topicService;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void findTopic() {
        // given
        TopicRequestDto topicRequestDto = new TopicRequestDto("탕수육은 부먹? 찍먹?");
        topicService.saveTopic(topicRequestDto);

        // when
        Topic todayTopic = topicService.findTopic();

        // then
        assertThat(todayTopic.getTitle()).isEqualTo("탕수육은 부먹? 찍먹?");
    }

}