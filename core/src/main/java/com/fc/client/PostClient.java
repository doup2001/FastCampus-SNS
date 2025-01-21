package com.fc.client;

import com.fc.domain.Post;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostClient {

    private Map<Long, Post> posts = new HashMap<>();

    public PostClient(){
        posts.put(1L, new Post(1L, 1L, "imageUrl1", "content1"));
        posts.put(2L, new Post(2L, 2L, "imageUrl2", "content2"));
        posts.put(3L, new Post(3L, 3L, "imageUrl3", "content3"));
        posts.put(4L, new Post(4L, 4L, "imageUrl4", "content4"));
        posts.put(5L, new Post(5L, 5L, "imageUrl5", "content5"));
    }

    public Post getPost(Long id){
        return posts.get(id);
    }

}
