// SearchResponse.java
package com.bmc.b_log.search;

import java.util.List;

public class SearchResponse {
    private List<String> postIds;
    private int totalPages;

    public SearchResponse(List<String> postIds, int totalPages) {
        this.postIds = postIds;
        this.totalPages = totalPages;
    }

    public List<String> getPostIds() {
        return postIds;
    }

    public int getTotalPages() {
        return totalPages;
    }
}