package com.backend.howaboutyou.service;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Opin;
import com.backend.howaboutyou.domain.Topic;
import com.backend.howaboutyou.dto.opin.OpinRequestDto;
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
class OpinServiceTest {

    @Autowired
    OpinService opinService;

    @PersistenceContext
    EntityManager em;

    @Test
    public void opinSaveTest() {
        // given
        Member member = createMember();
        em.persist(member);

        Topic topic = Topic.toEntity(new TopicRequestDto("title"));
        em.persist(topic);


        Member findMember = em.find(Member.class, member.getId());
        Topic findTopic = em.find(Topic.class, topic.getId());

        OpinRequestDto requestDto = new OpinRequestDto();
        requestDto.setMember(findMember);
        requestDto.setTopic(findTopic);
        requestDto.setVote(true);
        requestDto.setContent("역시 찍먹이지!");

        // when
        Long savedOpinId = opinService.save(requestDto);
        Opin findOpin = em.find(Opin.class, savedOpinId);

        // then
        assertThat(findOpin.getMember().getEmail()).isEqualTo(member.getEmail());
        assertThat(findOpin.getTopic().getTitle()).isEqualTo(topic.getTitle());
    }

    private static Member createMember() {
        Member member = Member.builder()
                .email("email")
                .password("password")
                .username("username")
                .build();
        return member;
    }

}