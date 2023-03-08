package com.backend.howaboutyou.dto.member;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    private String email;
    private String password;
    private String username;
}
