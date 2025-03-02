package com.bmc.b_log.repository;

import com.bmc.b_log.model.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Long> {
}
