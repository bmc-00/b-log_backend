package com.bmc.b_log.repository;

import com.bmc.b_log.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 활성화된 댓글 조회
    List<Comment> findByPostIdAndDeletedAtIsNull(Long postId);
}
