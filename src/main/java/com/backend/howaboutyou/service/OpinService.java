package com.backend.howaboutyou.service;

import com.backend.howaboutyou.domain.Opin;
import com.backend.howaboutyou.dto.opin.OpinResponseDto;
import com.backend.howaboutyou.dto.opin.OpinSaveDto;
import com.backend.howaboutyou.repository.opin.OpinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OpinService {

    private final OpinRepository opinRepository;

    public Long save(OpinSaveDto requestDto) {
        return opinRepository.save(Opin.toEntity(requestDto)).getId();
    }

    @Transactional(readOnly = true)
    public List<OpinResponseDto> opinAllSearch(Boolean condition) {
        return opinRepository.findAllOpin(condition);
    }

}
