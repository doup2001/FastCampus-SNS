package com.fc;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostClient {

    private Map<Long, Post> result = new HashMap<>();

    public PostClient(){
        result.put(1L, new Post(1L, 111L, "imageUrl1", "content1"));
        result.put(2L, new Post(2L, 222L, "imageUrl2", "content2"));
    }

    public Post getPost(Long id){
        return result.get(id);
    }

}
