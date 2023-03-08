package com.backend.howaboutyou.service;

import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.dto.member.SignupRequestDto;
import com.backend.howaboutyou.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long memberSave(SignupRequestDto requestDto) {
        return memberRepository.save(new Member(requestDto)).getId();
    }

}
