package com.backend.howaboutyou.service;

import com.backend.howaboutyou.domain.Opin;
import com.backend.howaboutyou.dto.opin.OpinRequestDto;
import com.backend.howaboutyou.repository.OpinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OpinService {

    private final OpinRepository opinRepository;

    public Long save(OpinRequestDto requestDto) {
        return opinRepository.save(Opin.toEntity(requestDto)).getId();
    }

}
