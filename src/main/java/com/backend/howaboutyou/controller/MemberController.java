package com.backend.howaboutyou.controller;

import com.backend.howaboutyou.config.JwtProvider;
import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.dto.member.LoginRequestDto;
import com.backend.howaboutyou.dto.member.LoginResponseDto;
import com.backend.howaboutyou.dto.member.SignupRequestDto;
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
        try {
            if (memberService.findMemberByEmail(requestDto.getEmail()).isPresent()) {
                throw new Exception("이미 가입된 이메일입니다.");
            }
            return ResponseEntity.ok().body(memberService.save(requestDto));
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> Login(@RequestBody LoginRequestDto requestDto) throws Exception {
        try {
            Optional<Member> memberOption = memberService.findMemberByEmail(requestDto.getEmail());
            if (memberOption.isEmpty()) {
                throw new Exception("가입되지 않은 이메일입니다.");
            }

            Member member = memberOption.get();
            if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
                throw new Exception("잘못된 비밀번호입니다.");
            }

            String token = jwtProvider.crateToken(member.getEmail());
            LoginResponseDto responseDto = new LoginResponseDto(
                    member.getUsername(),
                    member.getEmail(),
                    token
            );

            return ResponseEntity.ok().body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

}
