package com.backend.howaboutyou.repository.opin.custom;


import com.backend.howaboutyou.dto.opin.OpinResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.backend.howaboutyou.domain.QMember.member;
import static com.backend.howaboutyou.domain.QOpin.opin;
import static com.backend.howaboutyou.domain.QTopic.topic;

@Repository
@Transactional
@RequiredArgsConstructor
public class OpinCustomRepositoryImpl implements OpinCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional(readOnly = true)
    public List<OpinResponseDto> findAllOpin(Boolean voteCond) {
        return queryFactory
                .select(Projections.fields(OpinResponseDto.class,
                        member.username.as("user_name"),
                        member.grade.as("user_grade"),
                        topic.title.as("topic_title"),
                        opin.vote.as("vote"),
                        opin.content.as("content")))
                .from(opin)
                .join(opin.member, member)
                .join(opin.topic, topic)
                .where(voteIsTrueOrFalse(voteCond))
                .fetch();
    }

    BooleanExpression voteIsTrueOrFalse(Boolean vote) {
        if (vote == null) return null;
        else if (vote == true) return opin.vote.isTrue();
        else return opin.vote.isFalse();
    }

}
