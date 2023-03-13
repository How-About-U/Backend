package com.backend.howaboutyou.repository;

import com.backend.howaboutyou.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKeyEmail(String keyEmail);
    boolean existsByKeyEmail(String keyEmail);
    void deleteByKeyEmail(String keyEmail);
}
