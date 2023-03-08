package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.dto.member.SignupRequestDto;
import com.backend.howaboutyou.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> memberSave(@RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.ok().body(memberService.memberSave(requestDto));
    }

}
