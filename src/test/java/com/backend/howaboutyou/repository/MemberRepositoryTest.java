package com.backend.howaboutyou.repository;

import com.backend.howaboutyou.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberSaveTest() {
        // given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("test")
                .build();

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Assertions.assertThat(savedMember.getId()).isEqualTo(member.getId());
    }

}