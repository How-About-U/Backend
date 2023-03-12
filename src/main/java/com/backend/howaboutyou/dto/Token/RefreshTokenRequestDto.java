package com.backend.howaboutyou.dto.Token;

import com.backend.howaboutyou.domain.QRefreshToken;
import lombok.Builder;
import lombok.Data;

@Data
public class RefreshTokenRequestDto {
    private final String RefreshToken;
}
