package com.backend.howaboutyou.dto.topic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TopicDeleteResponseDto {

    private String title;

    public TopicDeleteResponseDto(String title) {
        this.title = title;
    }
}
