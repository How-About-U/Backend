package com.backend.howaboutyou.service;

import com.backend.howaboutyou.config.JwtProvider;
import com.backend.howaboutyou.domain.Member;
import com.backend.howaboutyou.domain.Opin;
import com.backend.howaboutyou.dto.opin.OpinRequestDto;
import com.backend.howaboutyou.dto.opin.OpinResponseDto;
import com.backend.howaboutyou.dto.opin.OpinSaveDto;
import com.backend.howaboutyou.repository.MemberRepository;
import com.backend.howaboutyou.repository.opin.OpinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OpinService {

    private final OpinRepository opinRepository;
    private final JwtService jwtService;
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    public Long save(OpinSaveDto requestDto) {
        return opinRepository.save(Opin.toEntity(requestDto)).getId();
    }

    public OpinResponseDto update(OpinRequestDto requestDto) {
        // 임시 유저정보 가져오기
        Member member = memberRepository.findByEmail(jwtProvider.getUserEmail(requestDto.getToken())).get();
        // 임시 opin정보 가져오기
        Opin opin = opinRepository.findById(requestDto.getOpinId()).get();

        opin.update(requestDto.getVote(), requestDto.getContent());
        return new OpinResponseDto().builder()
                .content(opin.getContent())
                .topic_title(opin.getTopic().getTitle())
                .user_grade(member.getGrade())
                .user_name(member.getUsername())
                .vote(opin.getVote())
                .build();
    }

    @Transactional(readOnly = true)
    public List<OpinResponseDto> opinAllSearch(Boolean condition) {
        return opinRepository.findAllOpin(condition);
    }

}
