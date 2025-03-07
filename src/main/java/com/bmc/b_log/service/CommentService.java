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
    
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(@RequestBody Comment request) {
        String hashedPassword = passwordEncoder.hashPassword(request.getPasswordHash());
        
        Comment comment = new Comment();
        comment.setPostId(request.getPostId());
        comment.setAuthor(request.getAuthor());
        comment.setPasswordHash(hashedPassword);
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setIpAddress(request.getIpAddress());
        comment.setProfileEmoji(request.getProfileEmoji());
    	
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdAndDeletedAtIsNull(postId);
    }

    public boolean deleteComment(Long commentId, String passwordHash) {
    	System.out.println(passwordHash);
        String hashedPassword = passwordEncoder.hashPassword(passwordHash);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment existingComment = comment.get();
            System.out.println(hashedPassword);
            System.out.println(existingComment.getPasswordHash());
            if (passwordEncoder.matches(existingComment.getPasswordHash(), hashedPassword)) {
                existingComment.setDeletedAt(LocalDateTime.now());
                commentRepository.save(existingComment);
                return true;
            }
        }
        return false;
    }
}
