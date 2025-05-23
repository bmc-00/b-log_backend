package com.bmc.b_log.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "posts") // Elasticsearch 인덱스 이름
public class PostDocument {

    @Id
    private String id; // ES 내부 ID

    private String postId; // 실제 DB Post ID

    @Field(type = FieldType.Text)
    private String combined; // title + summary + category 등을 합친 필드

    // 기본 생성자
    public PostDocument() {}

    // 전체 필드 생성자
    public PostDocument(String postId, String combined) {
        this.postId = postId;
        this.combined = combined;
    }

    // getter & setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCombined() {
        return combined;
    }

    public void setCombined(String combined) {
        this.combined = combined;
    }
}