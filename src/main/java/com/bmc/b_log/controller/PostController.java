package com.bmc.b_log.controller;

import com.bmc.b_log.model.Post;
import com.bmc.b_log.model.PostDetail;
import com.bmc.b_log.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/{id}")
    public PostDetail getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

}
