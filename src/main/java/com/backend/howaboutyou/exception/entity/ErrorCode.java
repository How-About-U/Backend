package com.backend.howaboutyou.exception.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

    // TOPIC
    TODAY_TOPIC_ALREADY(HttpStatus.BAD_REQUEST, "이미 오늘의 토픽이 존재합니다.");

    private final HttpStatus status;
    private String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
