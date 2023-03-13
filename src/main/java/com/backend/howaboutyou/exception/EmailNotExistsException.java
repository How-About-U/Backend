package com.backend.howaboutyou.exception;

import com.backend.howaboutyou.exception.entity.ErrorCode;
import lombok.Getter;

@Getter
public class EmailNotExistsException extends RuntimeException{
    private final ErrorCode errorCode;
    public EmailNotExistsException (ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
