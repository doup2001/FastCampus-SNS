package com.fc.in.client;

import com.fc.in.domain.Comment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommentClient {

    private Map<Long, Comment> result = new HashMap<>();

    public CommentClient(){
        result.put(1L, new Comment(1L, 111L, "imageUrl1", 1L, Instant.now()));
        result.put(2L, new Comment(2L, 222L, "imageUrl2", 1L, Instant.now()));
    }

    public Comment getComment(Long id){
        return result.get(id);
    }

}
