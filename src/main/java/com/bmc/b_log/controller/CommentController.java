package com.bmc.b_log.controller;

import com.bmc.b_log.model.Comment;
import com.bmc.b_log.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // ✅ 댓글 추가
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.addComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    // ✅ 특정 게시글의 댓글 조회
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@RequestParam Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    // ✅ 댓글 삭제 (비밀번호 검증)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestParam String passwordHash) {
        boolean deleted = commentService.deleteComment(commentId, passwordHash);
        if (deleted) {
            return ResponseEntity.ok("댓글이 삭제되었습니다.");
        } else {
            return ResponseEntity.status(403).body("비밀번호가 일치하지 않거나 댓글이 존재하지 않습니다.");
        }
    }
}
