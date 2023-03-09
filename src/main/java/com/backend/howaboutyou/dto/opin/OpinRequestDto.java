package com.backend.howaboutyou.dto.opin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpinRequestDto {
    private Boolean vote;
    private String content;
    private String token;
}
