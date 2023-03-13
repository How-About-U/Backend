package com.backend.howaboutyou.exception;

import com.backend.howaboutyou.exception.entity.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordMismatchException extends RuntimeException{
    private final ErrorCode errorCode;
    public PasswordMismatchException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
