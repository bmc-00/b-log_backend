package com.bmc.b_log.service;

import com.bmc.b_log.dto.PostSummaryDTO;
import com.bmc.b_log.model.Post;
import com.bmc.b_log.model.Tag;
import com.bmc.b_log.repository.PostRepository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        
    }

    public Page<PostSummaryDTO> getAllPostSummaries(int page, int size) {
    	  Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "created_at"));
    	  Page<Object[]> results = postRepository.findAllPostSummaries(pageable);
    	  List<PostSummaryDTO> dtos = results.getContent().stream().map(row -> 
          new PostSummaryDTO(
              (Long) row[0],  // id
              (String) row[1], // title
              (Integer) row[2],  // authorId
              (String) row[3], // summary
              (Timestamp) row[4], // createdAt
              (String) row[5], // category
              (String) row[6], // imageUrl
              (String) row[7], // tagNames
              (String) row[8]  // tagColors
          )
    	  ).collect(Collectors.toList());

          return new PageImpl<>(dtos, pageable, results.getTotalElements());
    }
    
    public Page<PostSummaryDTO> getPostSummariesByCategory(int page, int size, String category) {
  	  Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "created_at"));
  	  Page<Object[]> results = postRepository.findByCategory(category, pageable);
  	  List<PostSummaryDTO> dtos = results.getContent().stream().map(row -> 
        new PostSummaryDTO(
            (Long) row[0],  // id
            (String) row[1], // title
            (Integer) row[2],  // authorId
            (String) row[3], // summary
            (Timestamp) row[4], // createdAt
            (String) row[5], // category
            (String) row[6], // imageUrl
            (String) row[7], // tagNames
            (String) row[8]  // tagColors
        )
  	  ).collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, results.getTotalElements());
    }
    
    public Page<PostSummaryDTO> getPostSummariesByTag(int page, int size, String tag) {
    	  Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "created_at"));
    	  Page<Object[]> results = postRepository.findByTag(tag, pageable);
    	  List<PostSummaryDTO> dtos = results.getContent().stream().map(row -> 
          new PostSummaryDTO(
              (Long) row[0],  // id
              (String) row[1], // title
              (Integer) row[2],  // authorId
              (String) row[3], // summary
              (Timestamp) row[4], // createdAt
              (String) row[5], // category
              (String) row[6], // imageUrl
              (String) row[7], // tagNames
              (String) row[8]  // tagColors
          )
    	  ).collect(Collectors.toList());

          return new PageImpl<>(dtos, pageable, results.getTotalElements());
      }
    
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
    }
    
    public Post createPost(Post postDetail) {
    	return postRepository.save(postDetail);
    }
    
    public List<String> getAllCategories() {
        return postRepository.findAllCategories();
    }
}
