package com.backend.howaboutyou.constant;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum StatusCode {

    // TOPIC
    TOPIC_GET(HttpStatus.OK, "200 OK", "Topic - find successfully"),
    TOPIC_CREATE(HttpStatus.CREATED, "201 Created", "Topic - Create successfully"),
    TOPIC_UPDATE(HttpStatus.NO_CONTENT, "204 No Content", "Topic - Modified successfully"),
    TOPIC_DELETE(HttpStatus.NO_CONTENT,"204 No Content", "Topic - Delete successfully"),
    ERROR_TOPIC_NOTFOUND(HttpStatus.NOT_FOUND,"404 Not Found", "Topic - Not Found"),
    ERROR_TOPIC_ALREADY(HttpStatus.BAD_REQUEST,"400 Bad Request", "Today's topic already exists."),
    ERROR_TOPIC_CANT_CREATE(HttpStatus.FORBIDDEN, "403 Forbidden", "You don't have Permission to create the topic."),
    ERROR_TOPIC_CANT_UPDATE(HttpStatus.FORBIDDEN, "403 Forbidden", "You don't have Permission to modify the topic."),
    ERROR_TOPIC_CANT_DELETE(HttpStatus.FORBIDDEN, "403 Forbidden", "You don't have Permission to delete the topic.");

    private HttpStatus httpStatus;
    private String code;
    private String message;

    StatusCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
