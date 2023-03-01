package com.backend.howaboutyou.entity;

import com.backend.howaboutyou.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "topic_id")
    private Long id;

    private String title;

    @Builder
    public Topic(String title) {
        this.title = title;
    }
}
