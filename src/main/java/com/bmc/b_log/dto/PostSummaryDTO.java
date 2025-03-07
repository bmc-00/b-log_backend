package com.bmc.b_log.dto;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor // 모든 필드를 받는 생성자 추가
@NoArgsConstructor  // 기본 생성자 추가 (JPA에서 필요할 수 있음)
public class PostSummaryDTO {
    private Long id;
    private String title;
    private Long authorId;
    private String summary;
    private LocalDateTime createdAt;
    private String category;
    private String imageUrl;
    private String tagNames;
    private String tagColors;

    public PostSummaryDTO(Long id, String title, Long authorId, String summary, Timestamp createdAt, String category, String imageUrl, String tagNames, String tagColors) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.summary = summary;
        this.createdAt = createdAt != null ? createdAt.toLocalDateTime() : null; // 변환
        this.category = category;
        this.imageUrl = imageUrl;
        this.tagNames = tagNames;
        this.tagColors = tagColors;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Long getAuthorId() { return authorId; }
    public String getSummary() { return summary; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getCategory() { return category; }
    public String getImageUrl() { return imageUrl; }
    public String getTagNames() { return tagNames; }
    public String getTagColors() { return tagColors; }
}
