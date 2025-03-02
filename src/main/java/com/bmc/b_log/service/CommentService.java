package com.bmc.b_log.service;
import org.springframework.web.bind.annotation.*;

import com.bmc.b_log.Hasher;
import com.bmc.b_log.model.Comment;
import com.bmc.b_log.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private static final String SALT = "고정된_값";

    private final CommentRepository commentRepository;
    Hasher passwordEncoder = new Hasher();

    // ✅ 생성자를 통한 의존성 주입 (Spring 4.3+에서는 @Autowired 생략 가능)
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // ✅ 댓글 추가
    public Comment addComment(@RequestBody Comment request) {
        String hashedPassword = passwordEncoder.hashPassword(request.getPasswordHash());
        
        // 🔹 새로운 댓글 객체 생성
        Comment comment = new Comment();
        comment.setPostId(request.getPostId());
        comment.setAuthor(request.getAuthor());
        comment.setPasswordHash(hashedPassword); // 저장 시 해싱된 값 사용
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setIpAddress(request.getIpAddress());
        comment.setProfileEmoji(request.getProfileEmoji());
    	
        return commentRepository.save(comment);
    }

    // ✅ 특정 게시글의 댓글 조회 (삭제되지 않은 것만)
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdAndDeletedAtIsNull(postId);
    }

    // ✅ 댓글 삭제 (논리 삭제)
    public boolean deleteComment(Long commentId, String passwordHash) {
    	System.out.println(passwordHash);
        String hashedPassword = passwordEncoder.hashPassword(passwordHash);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment existingComment = comment.get();
            System.out.println(hashedPassword);
            System.out.println(existingComment.getPasswordHash());
            if (passwordEncoder.matches(existingComment.getPasswordHash(), hashedPassword)) {
                existingComment.setDeletedAt(LocalDateTime.now()); // 논리 삭제
                commentRepository.save(existingComment);
                return true;
            }
        }
        return false; // 비밀번호 불일치 또는 댓글이 존재하지 않음
    }
}
