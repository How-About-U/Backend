package com.backend.howaboutyou;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Opin;
import com.backend.howaboutyou.domain.Topic;
import com.backend.howaboutyou.repository.MemberRepository;
import com.backend.howaboutyou.repository.TopicRepository;
import com.backend.howaboutyou.repository.opin.OpinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class TestData {

    private final OpinRepository opinRepository;
    private final MemberRepository memberRepository;
    private final TopicRepository topicRepository;

    Member[] members = new Member[11];
    Topic topic;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDB() {
        createTopic();
        createMemberList();
        createOpinList();
    }

    private void createOpinList() {
        for (long j = 0; j < 10; j++) {
            Opin opin = Opin.builder()
                    .member(members[(int) j])
                    .topic(topic)
                    .vote(j % 3 == 0 ? true : false)
                    .content("content " + j)
                    .build();
            opinRepository.save(opin);
        }
    }

    private void createTopic() {
        topic = new Topic("topic");
        topicRepository.save(topic);
    }

    private void createMemberList() {
        for (int i = 0; i < 10; i++) {
            Member member = Member.builder()
                    .email("email" + i + "@test.com")
                    .password("1234" + i)
                    .username(UUID.randomUUID().toString().substring(0, 6))
                    .build();
            members[i] = member;
            memberRepository.save(member);
        }
    }
}
