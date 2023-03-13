package com.backend.howaboutyou.advice;

import com.backend.howaboutyou.constant.StatusCode;
import com.backend.howaboutyou.exception.entity.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private ErrorCode errorCode;
    private int status;
    private String code;
    private String message;


    public static ResponseEntity<ErrorResponse> toResponseEntity(StatusCode error){
        return ResponseEntity
                .status(error.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(error.getHttpStatus().value())
                        .code(error.getCode())
                        .message(error.getMessage())
                        .build()
                );
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.code = code;
        this.message = message;
    }
}
