package com.backend.howaboutyou.exception;

import com.backend.howaboutyou.exception.entity.ErrorCode;
import lombok.Getter;

@Getter
public class RefreshTokenMismatchException extends RuntimeException{
    private final ErrorCode errorCode;
    public RefreshTokenMismatchException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
