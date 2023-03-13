package com.backend.howaboutyou.exception.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"내부 서버 오류가 발생했습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 가입될 이메일입니다."),
    EMAIL_NOT_EXISTS(HttpStatus.BAD_REQUEST, "가입되지 않은 이메일입니다."),
    PASSWORD_MISS_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 틀립니다."),
    REFRESHTOKEN_NOT_EXISTS(HttpStatus.BAD_REQUEST, "존재하지 않는 리프레시 토큰입니다."),
    REFRESHTOKEN_MISMATCH(HttpStatus.BAD_REQUEST, "토큰과 유저의 정보가 일치하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
