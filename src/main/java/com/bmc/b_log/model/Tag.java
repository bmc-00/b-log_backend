package com.bmc.b_log.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;
  
  @Column(nullable = false)
  private String color;

  public Tag() {}

  public Tag(String name, String color) {
    this.name = name;
    this.color = color;
  }
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }
}
