package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.dto.topic.TopicRequestDto;
import com.backend.howaboutyou.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTopic(@RequestBody TopicRequestDto topicRequestDto) {
        log.info("TopicController.saveTopic()");
        return ResponseEntity.ok().body(topicService.saveTopic(topicRequestDto));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getTopic() {
        log.info("TopicController.getTopic()");
        return ResponseEntity.ok().body(topicService.getTodayTopic());
    }

}
