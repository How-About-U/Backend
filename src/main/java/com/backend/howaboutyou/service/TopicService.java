package com.backend.howaboutyou.service;

import com.backend.howaboutyou.constant.StatusCode;
import com.backend.howaboutyou.domain.Topic;
import com.backend.howaboutyou.dto.topic.TopicDeleteResponseDto;
import com.backend.howaboutyou.dto.topic.TopicRequestDto;
import com.backend.howaboutyou.dto.topic.TopicResponseDto;
import com.backend.howaboutyou.dto.topic.TopicUpdateResponseDto;
import com.backend.howaboutyou.exception.TopicAlreadyExistsException;
import com.backend.howaboutyou.exception.TopicNotFoundException;
import com.backend.howaboutyou.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicService {

    private final TopicRepository topicRepository;

    @Transactional
    public TopicResponseDto saveTopic(TopicRequestDto topicRequestDto) throws TopicAlreadyExistsException {
        Topic topic = Topic.toEntity(topicRequestDto);      // dto -> entity
        validateDuplicateTopic(topic);                      // 중복체크.
        return new TopicResponseDto(topicRepository.save(topic));
    }

    public Topic findTopic() {
        Topic findTodayTopic = topicRepository.findTopicByTopicDate(LocalDate.now()).orElseThrow(
                () -> new TopicNotFoundException(StatusCode.ERROR_TOPIC_NOTFOUND)
        );
        return findTodayTopic;
    }

    @Transactional
    public TopicUpdateResponseDto updateTopic(TopicRequestDto requestDto) {
        Topic topic = topicRepository.findByTopicDate(LocalDate.now()).orElseThrow(
                () -> new TopicNotFoundException(StatusCode.ERROR_TOPIC_CANT_UPDATE)
        );
        return new TopicUpdateResponseDto(topic.getTitle(), topic.update(requestDto), topic.getTopicDate());
    }

    @Transactional
    public TopicDeleteResponseDto deleteTopic() {
        Topic topic = topicRepository.findByTopicDate(LocalDate.now()).orElseThrow(
                () -> new TopicNotFoundException(StatusCode.ERROR_TOPIC_CANT_DELETE)
        );
        
        topicRepository.delete(topic);
        return new TopicDeleteResponseDto(topic.getTitle());
    }


    private void validateDuplicateTopic(Topic topic) {
        topicRepository.findByTopicDate(topic.getTopicDate())
                .ifPresent(
                        error -> {
                            throw new TopicAlreadyExistsException(StatusCode.ERROR_TOPIC_ALREADY);}
                );
    }
}
