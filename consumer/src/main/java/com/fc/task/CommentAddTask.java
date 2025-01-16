package com.fc.task;

import com.fc.*;
import com.fc.event.CommentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Log4j2
@Component
@RequiredArgsConstructor
public class CommentAddTask {

    private final PostClient postClient;
    private final CommentClient commentClient;
    private final NotificationSaveService saveService;

    public void processEvent(CommentEvent commentEvent) {

        Post post = postClient.getPost(commentEvent.getPostId());
        Comment comment = commentClient.getComment(commentEvent.getCommentId());

        // 글 작성자와 같다면 알림은 스킵
        if (post.getUserId()== commentEvent.getUserId()) {
            return;
        }

        // 알림 생성
        CommentNotification notification = createNotification(post, comment);

        // 저장
        saveService.insert(notification);

    }

    private CommentNotification createNotification(Post post, Comment comment) {
        Instant now = Instant.now();

        return new CommentNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.COMMENT,
                comment.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                post.getId(),
                comment.getUserId(),
                comment.getContent()
        );
    }

}
