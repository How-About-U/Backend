package com.backend.howaboutyou.exception;

import com.backend.howaboutyou.constant.StatusCode;
import lombok.Getter;

@Getter
public class TopicNotFoundException extends RuntimeException {
    StatusCode statusCode;

    public TopicNotFoundException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
