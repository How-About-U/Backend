package com.backend.howaboutyou.exception.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

    // TOPIC
    TODAY_TOPIC_ALREADY(HttpStatus.BAD_REQUEST, "이미 오늘의 토픽이 존재합니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"내부 서버 오류가 발생했습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 가입될 이메일입니다."),
    EMAIL_NOT_EXISTS(HttpStatus.BAD_REQUEST, "가입되지 않은 이메일입니다."),
    PASSWORD_MISS_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 틀립니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
