package com.backend.howaboutyou.service;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.dto.member.SignupRequestDto;
import com.backend.howaboutyou.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param requestDto
     */
    public Long save(SignupRequestDto requestDto) {
        Member member = Member.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        return memberRepository.save(member).getId();
    }

    /**
     * 이메일을 통해서 유저 찾기.
     * @param email
     */
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    /**
     * 모든 멤버 찾기.
     */
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

}
