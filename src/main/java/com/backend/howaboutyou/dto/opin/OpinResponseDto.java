package com.backend.howaboutyou.dto.opin;

import com.backend.howaboutyou.domain.constant.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpinResponseDto {

    /**
     * 응답 결과에는 사용자와 토픽 엔티티를 그대로 노출할 필요가 없기 때문에,
     * 필요한 데이터는 누가(+ 등급), 어떤 주제에, 어떤 의견을 남겼는지 정도면 될 거 같음.
     */
    private String user_name;
    private Grade user_grade;
    private String topic_title;
    private Boolean vote;
    private String content;
}
