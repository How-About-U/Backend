package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.config.JwtProvider;
import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.dto.member.LoginRequestDto;
import com.backend.howaboutyou.dto.member.LoginResponseDto;
import com.backend.howaboutyou.dto.member.SignupRequestDto;
import com.backend.howaboutyou.exception.EmailAlreadyExistsException;
import com.backend.howaboutyou.exception.EmailNotExistsException;
import com.backend.howaboutyou.exception.PasswordMismatchException;
import com.backend.howaboutyou.exception.entity.ErrorCode;
import com.backend.howaboutyou.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> memberSave(@RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.ok().body(memberService.save(requestDto));
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getMembers() {
        return ResponseEntity.ok().body(memberService.findAllMembers());
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<Object> Login(@RequestBody LoginRequestDto requestDto) {
        Member member = memberService.findMemberByEmail(requestDto.getEmail());
        memberService.comparePassword(requestDto.getPassword(), member.getPassword());
        LoginResponseDto responseDto = new LoginResponseDto(
                member.getUsername(),
                member.getEmail(),
                jwtProvider.crateToken(member.getEmail())
        );
        return ResponseEntity.ok().body(responseDto);
    }



}
