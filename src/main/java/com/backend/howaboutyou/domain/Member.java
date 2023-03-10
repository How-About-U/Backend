package com.backend.howaboutyou.domain;

import com.backend.howaboutyou.domain.base.BaseTimeEntity;
import com.backend.howaboutyou.domain.constant.Grade;
import com.backend.howaboutyou.dto.member.SignupRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Builder
    public Member(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.grade = Grade.BRONZE;
    }

    public Member(SignupRequestDto signupRequestDto) {
        this.email = signupRequestDto.getEmail();
        this.password = signupRequestDto.getPassword();
        this.username = signupRequestDto.getUsername();
        this.grade = Grade.BRONZE;
    }

    /**
     * 테스트 코드용 생성자
     */
    public Member(String username) {
        this.username = username;
    }
}
