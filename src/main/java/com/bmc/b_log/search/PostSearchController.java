package com.bmc.b_log.search;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/search")
public class PostSearchController {

    private final PostSearchService postSearchService;
    private final ForceSyncService forceSyncService;

    public PostSearchController(PostSearchService postSearchService, ForceSyncService forceSyncService) {
        this.postSearchService = postSearchService;
        this.forceSyncService = forceSyncService;
    }
    
    @GetMapping
    public Page<String> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return postSearchService.searchPostIds(keyword, page, size);
    }
    
    @PostMapping("/force-sync")
    public ResponseEntity<String> forceSync() {
        forceSyncService.forceSyncAll();
        return ResponseEntity.ok("Elasticsearch 인덱스 초기화 및 DB로부터 재동기화 완료");
    }
}