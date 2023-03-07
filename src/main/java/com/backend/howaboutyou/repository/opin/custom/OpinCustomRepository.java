package com.backend.howaboutyou.repository.opin.custom;


import com.backend.howaboutyou.dto.opin.OpinResponseDto;

import java.util.List;

public interface OpinCustomRepository {
    List<OpinResponseDto> findAllOpin(Boolean voteCond);
}
