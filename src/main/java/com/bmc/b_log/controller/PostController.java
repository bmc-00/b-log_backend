package com.bmc.b_log.controller;

import com.bmc.b_log.dto.PostSummaryDTO;
import com.bmc.b_log.model.Post;
import com.bmc.b_log.service.PostService;
import com.bmc.b_log.service.TagService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PostSummaryDTO>> getPostList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PostSummaryDTO> posts = postService.getAllPostSummaries(page, size);
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/bytag")
    public ResponseEntity<Page<PostSummaryDTO>> getPostListByTag(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
    		@RequestParam String tag) {
        Page<PostSummaryDTO> posts = postService.getPostSummariesByTag(page, size, tag);
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/bycategory")
    public ResponseEntity<Page<PostSummaryDTO>> getPostListByCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
    		@RequestParam String category) {
        Page<PostSummaryDTO> posts = postService.getPostSummariesByCategory(page, size, category);
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/getcategories")
    public List<String> getAllCategories() {
        return postService.getAllCategories();
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
