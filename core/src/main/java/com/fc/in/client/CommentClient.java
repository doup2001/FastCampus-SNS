package com.fc.in.client;

import com.fc.in.domain.Comment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommentClient {

    private Map<Long, Comment> comments = new HashMap<>();

    public CommentClient(){
        comments.put(1L, new Comment(1L, 1L, "content1", Instant.now()));
        comments.put(2L, new Comment(2L, 2L, "content2", Instant.now()));
        comments.put(3L, new Comment(3L, 3L, "content3", Instant.now()));
        comments.put(4L, new Comment(4L, 4L, "content4", Instant.now()));
        comments.put(5L, new Comment(5L, 5L, "content5", Instant.now()));
    }

    public Comment getComment(Long id){
        return comments.get(id);
    }

}
