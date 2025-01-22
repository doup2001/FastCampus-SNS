package com.fc.controller;

import com.fc.event.CommentEvent;
import com.fc.event.FollowEvent;
import com.fc.event.LikeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@RestController
public class EventConsumerTestController implements EventConsumerTestControllerSpec{

    @Autowired
    private Consumer<CommentEvent> comment;

    @Autowired
    private Consumer<LikeEvent> like;

    @Autowired
    private Consumer<FollowEvent> follow;


    @Override
    @PostMapping("/test/comment")
    public void comment(@RequestBody CommentEvent event) {
        comment.accept(event);
    }

    @Override
    @PostMapping("/test/like")
    public void like(@RequestBody LikeEvent event) {
        like.accept(event);
    }

    @Override
    @PostMapping("/test/follow")
    public void follow(@RequestBody FollowEvent event) {
        follow.accept(event);
    }

}
