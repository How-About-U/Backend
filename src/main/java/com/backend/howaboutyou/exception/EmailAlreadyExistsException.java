package com.backend.howaboutyou.exception;

import com.backend.howaboutyou.exception.entity.ErrorCode;
import lombok.Getter;

@Getter
public class EmailAlreadyExistsException extends RuntimeException{
    private final ErrorCode errorCode;
    public EmailAlreadyExistsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
