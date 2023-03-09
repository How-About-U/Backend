package com.backend.howaboutyou.dto.opin;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Topic;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpinSaveDto {
    private Boolean vote;
    private String content;
    private Member member;
    private Topic topic;
}
