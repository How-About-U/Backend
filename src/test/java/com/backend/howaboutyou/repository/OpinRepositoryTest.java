package com.backend.howaboutyou.repository;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Opin;
import com.backend.howaboutyou.domain.Topic;
import com.backend.howaboutyou.repository.opin.OpinRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OpinRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OpinRepository opinRepository;

    @Autowired
    TopicRepository topicRepository;

    @PersistenceContext
    EntityManager em;


    @Test
    public void opinTest() {
        // given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("test")
                .build();
        memberRepository.save(member);


        Topic topic = new Topic("탕수육은 찍먹이다? 부먹이다?", LocalDate.now());
        topicRepository.save(topic);

        Opin opin = Opin.builder()
                .member(member)
                .topic(topic)
                .vote(true)
                .content("탕수육은 당연히 찍먹이다!!!")
                .build();
        opinRepository.save(opin);


        // when
        Opin findOpin = opinRepository.findById(opin.getId()).orElseThrow(
                () -> new IllegalStateException("그딴거 없음.")
        );

        assertThat(findOpin.getMember().getId()).isEqualTo(member.getId());
        assertThat(findOpin.getTopic().getId()).isEqualTo(topic.getId());
    }

}