package com.backend.howaboutyou.repository;

import com.backend.howaboutyou.entity.Opin;
import com.backend.howaboutyou.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
