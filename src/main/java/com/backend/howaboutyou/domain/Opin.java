package com.backend.howaboutyou.domain;

import com.backend.howaboutyou.domain.base.BaseTimeEntity;
import com.backend.howaboutyou.dto.opin.OpinSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Opin extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    /**
     * 토픽에 대한 의견은 둘 중에 하나만 고르는 것이 컨셉이기 때문에,
     * Boolean 타입으로 true, false 로 선택.
     */
    private Boolean vote;

    private String content;

    @Builder
    public Opin(Member member, Topic topic, Boolean vote, String content) {
        this.member = member;
        this.topic = topic;
        this.vote = vote;
        this.content = content;
    }

    public static Opin toEntity(OpinSaveDto saveDto) {
        return Opin.builder()
                .vote(saveDto.getVote())
                .content(saveDto.getContent())
                .member(saveDto.getMember())
                .topic(saveDto.getTopic())
                .build();
    }
}
