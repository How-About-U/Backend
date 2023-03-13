package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.constant.StatusCode;
import com.backend.howaboutyou.dto.topic.TopicRequestDto;
import com.backend.howaboutyou.response.ResponseResult;
import com.backend.howaboutyou.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> topicSave(@RequestBody TopicRequestDto topicRequestDto) {
        log.info("TopicController.topicSave()");
        topicService.saveTopic(topicRequestDto);
        StatusCode topicCreate = StatusCode.TOPIC_CREATE;
        return new ResponseEntity<>(ResponseResult.builder()
                .statusCode(topicCreate)
                .code(topicCreate.getCode())
                .message(topicCreate.getMessage())
                .build(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/today")
    public ResponseEntity<?> topicGet() {
        log.info("TopicController.topicGet()");
        StatusCode topicGet = StatusCode.TOPIC_GET;
        return new ResponseEntity<>(ResponseResult.builder()
                .statusCode(topicGet)
                .code(topicGet.getCode())
                .message(topicGet.getMessage())
                .data(topicService.findTopic()).build(), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> topicUpdate(@RequestBody TopicRequestDto topicRequestDto) {
        log.info("TopicController.topicUpdate()");
        StatusCode topicUpdate = StatusCode.TOPIC_UPDATE;
        return new ResponseEntity<>(ResponseResult.builder()
                .statusCode(topicUpdate)
                .code(topicUpdate.getCode())
                .message(topicUpdate.getMessage())
                .data(topicService.updateTopic(topicRequestDto))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> topicDelete() {
        log.info("TopicController.topicDelete()");
        StatusCode topicDelete = StatusCode.TOPIC_DELETE;
        return new ResponseEntity<>(ResponseResult.builder()
                .statusCode(topicDelete)
                .code(topicDelete.getCode())
                .message(topicDelete.getMessage())
                .data(topicService.deleteTopic())
                .build(), HttpStatus.OK);
    }

}
