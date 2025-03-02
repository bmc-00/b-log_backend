package com.bmc.b_log.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;
    
    @Column(nullable = false, length = 10)
    private String profile_emoji;
    
    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, length = 255)
    private String passwordHash;  // ❗ 비밀번호 해싱 저장

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime deletedAt;  // ❗ 논리 삭제를 위한 필드

    @Column(length = 45)
    private String ipAddress;

    public Comment() {}

    // ✅ Getter & Setter 추가
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPasswordHash() { return passwordHash; }  // ✅ 추가
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }  // ✅ 추가

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getDeletedAt() { return deletedAt; }  // ✅ 추가
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }  // ✅ 추가

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    
    public String getProfileEmoji() { return profile_emoji; }
    public void setProfileEmoji(String profile_emoji) { this.profile_emoji = profile_emoji; }
}
