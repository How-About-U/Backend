package com.backend.howaboutyou.exception;

import com.backend.howaboutyou.constant.StatusCode;
import lombok.Getter;

@Getter
public class TopicAlreadyExistsException extends RuntimeException {
    private final StatusCode statusCode;

    public TopicAlreadyExistsException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
