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

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @GetMapping("/users")
    public ResponseEntity<Object> getMembers() {
        return ResponseEntity.ok().body(memberService.findAllMembers());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> memberSave(@RequestBody SignupRequestDto requestDto) {
        if (memberService.findMemberByEmail(requestDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        return ResponseEntity.ok().body(memberService.save(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> Login(@RequestBody LoginRequestDto requestDto) {

        Optional<Member> memberOption = memberService.findMemberByEmail(requestDto.getEmail());
        if (memberOption.isEmpty()) {
            throw new EmailNotExistsException(ErrorCode.EMAIL_NOT_EXISTS);
        }

        Member member = memberOption.get();
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new PasswordMismatchException(ErrorCode.PASSWORD_MISS_MATCH);
        }

        String token = jwtProvider.crateToken(member.getEmail());
        LoginResponseDto responseDto = new LoginResponseDto(
                member.getUsername(),
                member.getEmail(),
                token
        );

        return ResponseEntity.ok().body(responseDto);
    }

}
