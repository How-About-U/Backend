package com.backend.howaboutyou.advice;

import com.backend.howaboutyou.exception.TopicAlreadyExistsException;
import com.backend.howaboutyou.exception.TopicNotFoundException;
import com.backend.howaboutyou.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(TopicAlreadyExistsException.class)
    protected ResponseEntity<?> TopicAlreadyExceptionHandler(final TopicAlreadyExistsException e) {
        log.error("TopicAlreadyExistsException : " + e.getStatusCode().getMessage());
        return ErrorResponse.toResponseEntity(e.getStatusCode());
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<?> TopicNotFoundExceptionHandler(final TopicNotFoundException e) {
        log.error("TopicNotFoundException : " + e.getStatusCode().getMessage());
        return ErrorResponse.toResponseEntity(e.getStatusCode());
    }


}
