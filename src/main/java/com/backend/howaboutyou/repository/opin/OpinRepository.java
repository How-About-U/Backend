package com.backend.howaboutyou.repository.opin;

import com.backend.howaboutyou.domain.Opin;
import com.backend.howaboutyou.repository.opin.custom.OpinCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinRepository extends JpaRepository<Opin, Long>, OpinCustomRepository {
}
