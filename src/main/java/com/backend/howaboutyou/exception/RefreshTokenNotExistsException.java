package com.backend.howaboutyou.exception;

import com.backend.howaboutyou.exception.entity.ErrorCode;
import lombok.Getter;
@Getter
public class RefreshTokenNotExistsException extends RuntimeException{
    private final ErrorCode errorCode;
    public RefreshTokenNotExistsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
