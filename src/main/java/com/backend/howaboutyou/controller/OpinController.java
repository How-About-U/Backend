package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.service.OpinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/opin")
public class OpinController {

    private final OpinService opinService;

    @GetMapping("/get")
    public ResponseEntity<?> opinSearch(@Nullable @RequestBody Boolean searchCond) {
        log.info("OpinController.opinSearch");
        return ResponseEntity.ok().body(opinService.opinAllSearch(searchCond));
    }
}
