package com.backend.howaboutyou.domain;

import com.backend.howaboutyou.dto.TopicRequestDto;
import com.backend.howaboutyou.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "topic_id")
    private Long id;

    private String title;

    @Column(unique = true)
    private LocalDate topicDate;

    public Topic(String title, LocalDate topicDate) {
        this.title = title;
        this.topicDate = topicDate;
    }

    public static Topic toEntity(TopicRequestDto requestDto) {
        return new Topic(requestDto.getTitle(), LocalDate.now());
    }

}
