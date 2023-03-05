package com.backend.howaboutyou.advice;

import com.backend.howaboutyou.exception.TopicAlreadyExistsException;
import com.backend.howaboutyou.exception.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TopicExceptionAdvice {

    @ExceptionHandler(TopicAlreadyExistsException.class)
    protected ResponseEntity<?> TopicAlreadyExceptionHandler(final TopicAlreadyExistsException e) {
        log.error("TopicAlreadyExistsException : " + e.getErrorCode().getMessage());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }


}
