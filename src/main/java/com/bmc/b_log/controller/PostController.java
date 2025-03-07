package com.bmc.b_log.controller;

import com.bmc.b_log.dto.PostSummaryDTO;
import com.bmc.b_log.model.Post;
import com.bmc.b_log.service.PostService;
import com.bmc.b_log.service.TagService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final TagService tagService;

    public PostController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<PostSummaryDTO> getAllPostSummaries() {
        return postService.getAllPostSummaries();
    }
    
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
    
    @PostMapping("/upload")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestParam List<String> tags) {
        Post savedPost = postService.createPost(post);
        tagService.assignTagsToPost(savedPost, tags); // 게시글에 태그 할당
        return ResponseEntity.ok(savedPost);
    }
}
