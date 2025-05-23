package com.bmc.b_log.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostSearchRepository extends ElasticsearchRepository<PostDocument, String> {

    @Query("{\"match\": {\"combined\": \"?0\"}}")
    Page<PostDocument> findByCombined(String keyword, Pageable pageable);
}