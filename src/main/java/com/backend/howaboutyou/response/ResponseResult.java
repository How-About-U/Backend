package com.backend.howaboutyou.response;


import com.backend.howaboutyou.constant.StatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    private StatusCode statusCode;
    private String code;
    private String message;
    private T data;

    @Builder
    public ResponseResult(StatusCode statusCode, String code, String message, T data) {
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
