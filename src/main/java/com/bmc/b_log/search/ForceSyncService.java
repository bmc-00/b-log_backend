package com.bmc.b_log.search;

import com.bmc.b_log.model.Post;
import com.bmc.b_log.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForceSyncService {

    private final PostRepository postRepository;
    private final PostSearchRepository postSearchRepository;

    public ForceSyncService(PostRepository postRepository, PostSearchRepository postSearchRepository) {
        this.postRepository = postRepository;
        this.postSearchRepository = postSearchRepository;
    }

    public void forceSyncAll() {
        // 1. 기존 Elasticsearch 인덱스 비우기
        postSearchRepository.deleteAll();

        // 2. DB에서 모든 게시글 조회
        List<Post> posts = postRepository.findAll();

        // 3. 변환 후 Elasticsearch에 저장
        List<PostDocument> documents = new ArrayList<>();
        for (Post post : posts) {
            StringBuilder combined = new StringBuilder();
            combined.append(post.getTitle()).append(" ");
            combined.append(post.getContent()).append(" ");
            combined.append(post.getCategory()).append(" ");
            combined.append(post.getSummary()).append(" ");

            PostDocument doc = new PostDocument(String.valueOf(post.getId()), combined.toString());
            documents.add(doc);
        }

        postSearchRepository.saveAll(documents);
    }
}