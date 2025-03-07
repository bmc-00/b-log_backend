package com.bmc.b_log.repository;

import com.bmc.b_log.dto.PostSummaryDTO;
import com.bmc.b_log.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  
	@Query(value = """
		    SELECT 
		        p.id, p.title, p.author_id, p.summary, p.created_at, 
		        p.category, p.image_url, 
		        COALESCE((SELECT STRING_AGG(t.name, ',') 
		                  FROM post_tags pt 
		                  JOIN tags t ON pt.tag_id = t.id 
		                  WHERE pt.post_id = p.id), '') AS tag_names, 
		        COALESCE((SELECT STRING_AGG(t.color, ',') 
		                  FROM post_tags pt 
		                  JOIN tags t ON pt.tag_id = t.id 
		                  WHERE pt.post_id = p.id), '') AS tag_colors
		    FROM posts p
		    """, nativeQuery = true)
	List<Object[]> findAllPostSummaries();

}
