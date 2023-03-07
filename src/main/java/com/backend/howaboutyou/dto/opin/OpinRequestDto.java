package com.backend.howaboutyou.dto.opin;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpinRequestDto {
    private Member member;
    private Topic topic;
    private Boolean vote;
    private String content;
}
