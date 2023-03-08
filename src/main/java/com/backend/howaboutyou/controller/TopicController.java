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
    public ResponseEntity<?> topicSave(@RequestBody TopicRequestDto topicRequestDto) {
        log.info("TopicController.topicSave");
        return ResponseEntity.ok().body(topicService.saveTopic(topicRequestDto));
    }

    @GetMapping("/get")
    public ResponseEntity<?> topicGet() {
        log.info("TopicController.get");
        return ResponseEntity.ok().body(topicService.getTodayTopic());
    }

}
