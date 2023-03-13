package com.backend.howaboutyou.domain;

import com.backend.howaboutyou.dto.topic.TopicRequestDto;
import com.backend.howaboutyou.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String update(TopicRequestDto requestDto) {
        this.title = requestDto.getTitle();
        return title;
    }

    /**
     * 테스트용 코드 생성자
     * @param title
     */
    public Topic(String title) {
        this.title = title;
    }

}
