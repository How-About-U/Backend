package com.backend.howaboutyou.service;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.dto.member.SignupRequestDto;
import com.backend.howaboutyou.exception.EmailAlreadyExistsException;
import com.backend.howaboutyou.exception.EmailNotExistsException;
import com.backend.howaboutyou.exception.PasswordMismatchException;
import com.backend.howaboutyou.exception.entity.ErrorCode;
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
     * @param requestDto 클라이언트에서 요청한 멤버 가입정보
     */
    public Long save(SignupRequestDto requestDto) {
        if (memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        Member member = Member.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        return memberRepository.save(member).getId();
    }

    /**
     * 이메일을 통해서 유저 찾기.
     * @param email 클라이언트에서 요청한 이메일
     */
    public Member findMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            throw new EmailNotExistsException(ErrorCode.EMAIL_NOT_EXISTS);
        }
        return member.get();
    }

    /**
     * 모든 멤버 찾기.
     */
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    /**
    * 비밀번호 비교하기.
     * @param requestPassword 클라이언트에서 요청한 비밀번호
     * @param hashedPassword 데이터베이스에 저장된 비밀번호
    */
    public void comparePassword(String requestPassword, String hashedPassword) {
        if (!passwordEncoder.matches(requestPassword, hashedPassword)) {
            throw new PasswordMismatchException(ErrorCode.PASSWORD_MISS_MATCH);
        }
    }

}
