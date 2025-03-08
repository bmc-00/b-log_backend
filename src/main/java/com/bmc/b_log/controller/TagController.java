package com.bmc.b_log.controller;

import com.bmc.b_log.model.Tag;
import com.bmc.b_log.model.Post;
import com.bmc.b_log.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // 모든 태그 조회
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
}
