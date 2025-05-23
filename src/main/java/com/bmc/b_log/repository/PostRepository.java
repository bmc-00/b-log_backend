package com.bmc.b_log.repository;

import com.bmc.b_log.dto.PostSummaryDTO;
import com.bmc.b_log.model.Post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  
    @Query(value = """
            SELECT p.id, p.title, p.author_id, p.summary, p.created_at, 
                   p.category, p.image_url,
                   (SELECT STRING_AGG(t.name, ',') 
                    FROM post_tags pt 
                    JOIN tags t ON pt.tag_id = t.id 
                    WHERE pt.post_id = p.id) as tag_names,
                   (SELECT STRING_AGG(t.color, ',') 
                    FROM post_tags pt 
                    JOIN tags t ON pt.tag_id = t.id 
                    WHERE pt.post_id = p.id) as tag_colors
            FROM posts p
            """, 
            countQuery = "SELECT COUNT(*) FROM posts", 
            nativeQuery = true)
    Page<Object[]> findAllPostSummaries(Pageable pageable);
    
    @Query(value = """
    		SELECT p.id, p.title, p.author_id, p.summary, p.created_at, 
                   p.category, p.image_url,
                   (SELECT STRING_AGG(t.name, ',') 
                    FROM post_tags pt 
                    JOIN tags t ON pt.tag_id = t.id 
                    WHERE pt.post_id = p.id) as tag_names,
                   (SELECT STRING_AGG(t.color, ',') 
                    FROM post_tags pt 
                    JOIN tags t ON pt.tag_id = t.id 
                    WHERE pt.post_id = p.id) as tag_colors
            FROM posts p
            WHERE p.category = :category
    	   """,  countQuery = "SELECT COUNT(*) FROM posts p WHERE p.category = :category",  nativeQuery = true)
    Page<Object[]> findByCategory(@Param("category") String category, Pageable pageable);

    @Query(value = """
    		SELECT p.id, p.title, p.author_id, p.summary, p.created_at, 
                   p.category, p.image_url,
                   (SELECT STRING_AGG(t.name, ',') 
                    FROM post_tags pt 
                    JOIN tags t ON pt.tag_id = t.id 
                    WHERE pt.post_id = p.id) as tag_names,
                   (SELECT STRING_AGG(t.color, ',') 
                    FROM post_tags pt 
                    JOIN tags t ON pt.tag_id = t.id 
                    WHERE pt.post_id = p.id) as tag_colors
            FROM posts p
            WHERE p.id IN (select pt.post_id
                           FROM post_tags pt
                           JOIN tags t ON pt.tag_id = t.id
                           WHERE t.name = :tag)
    	   """, countQuery = "SELECT COUNT(*) FROM posts p WHERE p.id IN (select pt.post_id\n"
    	   		+ "                           FROM post_tags pt\n"
    	   		+ "                           JOIN tags t ON pt.tag_id = t.id\n"
    	   		+ "                           WHERE t.name = :tag)", nativeQuery = true)
    Page<Object[]> findByTag(@Param("tag") String tag, Pageable pageable);
    
    @Query(value = """
    		select DISTINCT p.category FROM posts p;
    		""",nativeQuery = true)
    List<String> findAllCategories();
    
    @Query(value = """
    		SELECT p.id, p.title, p.author_id, p.summary, p.created_at, 
                   p.category, p.image_url,
                   (SELECT STRING_AGG(t.name, ',') 
                    FROM post_tags pt 
                    JOIN tags t ON pt.tag_id = t.id 
                    WHERE pt.post_id = p.id) as tag_names,
                   (SELECT STRING_AGG(t.color, ',') 
                    FROM post_tags pt 
                    JOIN tags t ON pt.tag_id = t.id 
                    WHERE pt.post_id = p.id) as tag_colors
            FROM posts p
            WHERE p.id = :id
    		""", 
            countQuery = "SELECT COUNT(*) FROM posts", 
            nativeQuery = true)
    Object[][] findPostSummary(Long id);
}
