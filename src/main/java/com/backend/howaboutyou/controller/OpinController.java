package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.config.JwtProvider;
import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Topic;
import com.backend.howaboutyou.dto.opin.OpinRequestDto;
import com.backend.howaboutyou.dto.opin.OpinSaveDto;
import com.backend.howaboutyou.repository.TopicRepository;
import com.backend.howaboutyou.service.JwtService;
import com.backend.howaboutyou.service.MemberService;
import com.backend.howaboutyou.service.OpinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/opin")
public class OpinController {
    private final TopicRepository topicRepository;
    private final OpinService opinService;
    private final JwtProvider jwtProvider;
    private final MemberService memberService;
    private final JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> opinSave(@RequestBody OpinRequestDto requestDto) {
        // JWT -> 유저 정보 추출.
        Member member = memberService.findMemberByEmail(
                jwtProvider.getUserEmail(requestDto.getToken())
        );

        String newAccessToken = jwtService.getNewAccessToken(member.getEmail());

        // 만약 null이면 새로 로그인 필요
        if (newAccessToken == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("로그인이 필요합니다.");
        }

        // 임시로 토픽을 임의로 생성.
        Topic testTopic = new Topic("탕수육은 부먹? 아니면 찍먹?");
        topicRepository.save(testTopic);

        OpinSaveDto saveDto = new OpinSaveDto(
                requestDto.getVote(),
                requestDto.getContent(),
                member,
                testTopic
        );
        return ResponseEntity.ok().body(opinService.save(saveDto));
    }

    @GetMapping("/list")
    public ResponseEntity<?> opinListSearch(@Nullable @RequestParam Boolean searchCond) {
        log.info("OpinController.opinSearch");
        return ResponseEntity.ok().body(opinService.opinAllSearch(searchCond));
    }
}
