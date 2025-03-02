package com.bmc.b_log.service;

import com.bmc.b_log.model.Post;
import com.bmc.b_log.model.PostDetail;
import com.bmc.b_log.repository.PostRepository;
import com.bmc.b_log.repository.PostDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostDetailRepository postDetailRepository;

    public PostService(PostRepository postRepository, PostDetailRepository postDetailRepository) {
        this.postRepository = postRepository;
        this.postDetailRepository = postDetailRepository;
        
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    public PostDetail getPostById(Long id) {
        return postDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
    }
}
