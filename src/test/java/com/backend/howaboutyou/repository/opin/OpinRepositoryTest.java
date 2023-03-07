package com.backend.howaboutyou.repository.opin;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Opin;
import com.backend.howaboutyou.domain.Topic;
import com.backend.howaboutyou.dto.opin.OpinResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class OpinRepositoryTest {

    @Autowired
    OpinRepository opinRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void findAllOpin_Custom_Test() {
        // given
        Member member = new Member("test Member");
        em.persist(member);

        Topic topic = new Topic("test Topic");
        em.persist(topic);

        Opin opinNull = Opin.builder()
                .member(member)
                .topic(topic)
                .vote(null)
                .content("null")
                .build();
        em.persist(opinNull);

        Opin opinTrue = Opin.builder()
                .member(member)
                .topic(topic)
                .vote(true)
                .content("true")
                .build();
        em.persist(opinTrue);

        Opin opinFalse = Opin.builder()
                .member(member)
                .topic(topic)
                .vote(false)
                .content("false")
                .build();
        em.persist(opinFalse);

        em.flush();
        em.clear();


        // when
        List<OpinResponseDto> nullOpin = opinRepository.findAllOpin(null);
        System.out.println(nullOpin.get(0).getVote());
        List<OpinResponseDto> trueOpin = opinRepository.findAllOpin(true);
        System.out.println(trueOpin.get(0).getVote());
        List<OpinResponseDto> falseOpin = opinRepository.findAllOpin(false);
        System.out.println(falseOpin.get(0).getVote());
    }
}