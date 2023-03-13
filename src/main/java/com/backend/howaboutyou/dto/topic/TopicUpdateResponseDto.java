package com.backend.howaboutyou.dto.topic;

import com.backend.howaboutyou.domain.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TopicUpdateResponseDto {

    private String prevTitle;
    private String curTitle;
    private LocalDate topicDate;

    public TopicUpdateResponseDto(String prevTitle, String curTitle, LocalDate topicDate) {
        this.prevTitle = prevTitle;
        this.curTitle = curTitle;
        this.topicDate = topicDate;
    }
}
