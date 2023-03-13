package com.backend.howaboutyou.dto.Token;

import com.backend.howaboutyou.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDto {
    private final String status;
    private final String message;
    private String token;
}
