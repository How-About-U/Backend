package com.backend.howaboutyou.repository;

import com.backend.howaboutyou.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
