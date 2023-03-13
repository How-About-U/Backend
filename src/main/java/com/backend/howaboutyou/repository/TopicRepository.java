package com.backend.howaboutyou.repository;

import com.backend.howaboutyou.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findTopicByTopicDate(LocalDate topicDate);
    Optional<Topic> findByTopicDate(LocalDate topicDate);
}
