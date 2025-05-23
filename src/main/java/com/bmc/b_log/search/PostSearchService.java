package com.bmc.b_log.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSearchService {

    private final PostSearchRepository postSearchRepository;

    public PostSearchService(PostSearchRepository postSearchRepository) {
        this.postSearchRepository = postSearchRepository;
    }

    public Page<String> searchPostIds(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postSearchRepository.findByCombined(keyword, pageable)
                .map(PostDocument::getPostId); // Page<PostDocument> → Page<String>
    }
}