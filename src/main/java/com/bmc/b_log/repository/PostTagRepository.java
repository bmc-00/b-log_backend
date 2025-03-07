package com.bmc.b_log.repository;

import com.bmc.b_log.model.PostTag;
import com.bmc.b_log.model.Post;
import com.bmc.b_log.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    List<PostTag> findByPost(Post post); // 특정 게시글의 태그 목록 조회
    List<PostTag> findByTag(Tag tag); // 특정 태그를 사용한 게시글 조회
    void deleteByPost(Post post); // 게시글 삭제 시 태그 연결도 삭제 (태그 자체는 유지)
}
