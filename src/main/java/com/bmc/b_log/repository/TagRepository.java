package com.bmc.b_log.repository;

import com.bmc.b_log.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name); //이름으로 조회해 중복 저장 방지 
}
