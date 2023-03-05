package com.backend.howaboutyou.exception;

import com.backend.howaboutyou.exception.entity.ErrorCode;
import lombok.Getter;

@Getter
public class TopicAlreadyExistsException extends RuntimeException {
    ErrorCode errorCode;

    public TopicAlreadyExistsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
