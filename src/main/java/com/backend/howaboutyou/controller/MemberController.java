package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.config.JwtProvider;
import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Token;
import com.backend.howaboutyou.dto.member.LoginRequestDto;
import com.backend.howaboutyou.dto.member.LoginResponseDto;
import com.backend.howaboutyou.dto.member.SignupRequestDto;
import com.backend.howaboutyou.service.JwtService;
import com.backend.howaboutyou.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final JwtService jwtService;

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> memberSave(@RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.ok().body(memberService.save(requestDto));
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<Object> Login(@RequestBody LoginRequestDto requestDto) {
        Member member = memberService.findMemberByEmail(requestDto.getEmail());

        memberService.comparePassword(requestDto.getPassword(), member.getPassword());

        // 데이터베이스에 Refresh Token 저장
        Token token = jwtProvider.createAccessToken(member.getEmail());
        jwtService.login(token);

        LoginResponseDto responseDto = new LoginResponseDto(
                member.getUsername(),
                member.getEmail(),
                token.getAccessToken()
        );
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getMembers() {
        return ResponseEntity.ok().body(memberService.findAllMembers());
    }


}
