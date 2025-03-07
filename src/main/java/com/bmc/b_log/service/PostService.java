package com.bmc.b_log.service;

import com.bmc.b_log.dto.PostSummaryDTO;
import com.bmc.b_log.model.Post;
import com.bmc.b_log.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        
    }

    public List<PostSummaryDTO> getAllPostSummaries() {
    	  List<Object[]> results = postRepository.findAllPostSummaries();
    	  return results.stream()
    	      .map(row -> new PostSummaryDTO(
    	          ((Number) row[0]).longValue(),  // id
    	          (String) row[1],  // title
    	          ((Number) row[2]).longValue(),  // authorId
    	          (String) row[3],  // summary
    	          (Timestamp) row[4],  // createdAt
    	          (String) row[5],  // category
    	          (String) row[6],  // imageUrl
    	          (String) row[7],  // tagNames
    	          (String) row[8]   // tagColors
    	      ))
    	      .toList();
    	}
    
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
    }
    
    public Post createPost(Post postDetail) {
    	return postRepository.save(postDetail);
    }
}
