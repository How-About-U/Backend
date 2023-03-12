package com.backend.howaboutyou.exception.entity;

import com.backend.howaboutyou.exception.EmailAlreadyExistsException;
import com.backend.howaboutyou.exception.EmailNotExistsException;
import com.backend.howaboutyou.exception.PasswordMismatchException;
import com.backend.howaboutyou.exception.TopicAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handlerException(Exception e) {
        log.error("Exception: " + e);
        ErrorResponse response = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TopicAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleTopicAlreadyExistsException(TopicAlreadyExistsException e) {
        log.error("handleTopicAlreadyExistsException: ", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getStatus());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        log.error("handleEmailAlreadyExistsException: ", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getStatus());
    }

    @ExceptionHandler(EmailNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotExistsException(EmailNotExistsException e) {
        log.error("handleEmailNotExistsException: ", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getStatus());
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException e) {
        log.error("handlePasswordMismatchException: ", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getStatus());
    }
}
