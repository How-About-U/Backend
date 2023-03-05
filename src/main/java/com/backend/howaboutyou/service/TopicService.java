package com.backend.howaboutyou.service;

import com.backend.howaboutyou.exception.entity.ErrorCode;
import com.backend.howaboutyou.dto.TopicRequestDto;
import com.backend.howaboutyou.dto.TopicResponseDto;
import com.backend.howaboutyou.domain.Topic;
import com.backend.howaboutyou.exception.TopicAlreadyExistsException;
import com.backend.howaboutyou.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicService {

    private final TopicRepository topicRepository;

    @Transactional
    public Long saveTopic(TopicRequestDto topicRequestDto) throws EntityExistsException {

        if (topicRepository.existsByTopicDate(LocalDate.now()))
            throw new TopicAlreadyExistsException(ErrorCode.TODAY_TOPIC_ALREADY);

        return topicRepository.save(Topic.toEntity(topicRequestDto)).getId();
    }

    public TopicResponseDto getTodayTopic() {
        Topic findTodayTopic = topicRepository.findTopicByTopicDate(LocalDate.now()).orElseThrow(
                () -> new EntityNotFoundException("해당 날짜의 Topic 은 존재하지 않습니다.")
        );

        return new TopicResponseDto(findTodayTopic);
    }

}
