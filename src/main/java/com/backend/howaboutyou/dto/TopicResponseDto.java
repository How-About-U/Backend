package com.backend.howaboutyou.dto;

import com.backend.howaboutyou.domain.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TopicResponseDto {

    private Long id;
    private String title;
    private LocalDate topicDate;

    public TopicResponseDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.topicDate = topic.getTopicDate();
    }
}
