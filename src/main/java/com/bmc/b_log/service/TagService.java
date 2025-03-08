package com.bmc.b_log.service;
import java.util.Random;
import com.bmc.b_log.model.Tag;
import com.bmc.b_log.model.Post;
import com.bmc.b_log.model.PostTag;
import com.bmc.b_log.repository.TagRepository;
import com.bmc.b_log.repository.PostTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    public TagService(TagRepository tagRepository, PostTagRepository postTagRepository) {
        this.tagRepository = tagRepository;
        this.postTagRepository = postTagRepository;
    }

    // 태그 추가 (중복 방지 및 색상 자동 생성 )
    public Tag createTag(String tagName) {
    	Random random = new Random();
        int r = 200 + random.nextInt(56);
        int g = 200 + random.nextInt(56);
        int b = 200 + random.nextInt(56);
        String tagColor = String.format("#%02X%02X%02X", r, g, b);
    	Tag tag = tagRepository.findByName(tagName)
    		    .orElse(new Tag(tagName, tagColor));
    	return tagRepository.save(tag);
    }

    // 모든 태그 조회
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    // 게시글에 태그 할당
    public void assignTagsToPost(Post post, List<String> tagNames) {
        for (int i = 0; i < tagNames.size(); i++) {
            String tagName = tagNames.get(i);

            // 태그가 존재하면 가져오고, 없으면 새로 생성
            Tag tag = createTag(tagName);

            // PostTag 객체 생성 및 저장
            PostTag postTag = new PostTag();
            postTag.setPost(post);
            postTag.setTag(tag);
            postTagRepository.save(postTag);
        }
    }
}
