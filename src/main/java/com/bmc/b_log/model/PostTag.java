package com.bmc.b_log.model;

import jakarta.persistence.*;

@Entity
@Table(name = "post_tags")
public class PostTag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @ManyToOne
  @JoinColumn(name = "tag_id", nullable = false)
  private Tag tag;

  public PostTag() {}
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Post getPost() { return post; }
  public void setPost(Post post) { this.post = post; }

  public Tag getTag() { return tag; }
  public void setTag(Tag tag) { this.tag = tag; }
}

