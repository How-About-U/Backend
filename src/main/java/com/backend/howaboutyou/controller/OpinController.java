package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.dto.opin.OpinRequestDto;
import com.backend.howaboutyou.service.OpinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/opin")
public class OpinController {

    private final OpinService opinService;

    @PostMapping("/save")
    public ResponseEntity<?> opinSave(@RequestBody OpinRequestDto requestDto) {
        return ResponseEntity.ok().body(opinService.save(requestDto));
    }

    @GetMapping("/get")
    public ResponseEntity<?> opinSearch(@Nullable @RequestBody Boolean searchCond) {
        log.info("OpinController.opinSearch");
        return ResponseEntity.ok().body(opinService.opinAllSearch(searchCond));
    }
}
