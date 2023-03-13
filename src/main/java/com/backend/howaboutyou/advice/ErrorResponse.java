package com.backend.howaboutyou.advice;

import com.backend.howaboutyou.constant.StatusCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponse {
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
}
