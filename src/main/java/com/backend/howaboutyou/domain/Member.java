package com.backend.howaboutyou.domain;

import com.backend.howaboutyou.domain.base.BaseTimeEntity;
import com.backend.howaboutyou.domain.constant.Grade;
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
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String username;

    private Grade grade;

    @Builder
    public Member(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        // 브론즈 등급부터 시작.
        this.grade = Grade.BRONZE;
    }

    /**
     * 테스트 코드용 생성자
     * @param username
     */
    public Member(String username) {
        this.username = username;
    }
}
