package com.fc.task;

import com.fc.in.domain.Comment;
import com.fc.in.client.CommentClient;
import com.fc.in.domain.Post;
import com.fc.in.client.PostClient;
import com.fc.domain.comment.CommentNotification;
import com.fc.event.CommentEvent;
import com.fc.domain.notificaition.NotificationIdGenerator;
import com.fc.domain.notificaition.NotificationType;
import com.fc.out.NotificationSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Log4j2
@Component
@RequiredArgsConstructor
public class CommentAddTask {

    private final PostClient postClient;
    private final CommentClient commentClient;
    private final NotificationSaveService saveService;

    public void processEvent(CommentEvent commentEvent) {

        Post post = postClient.getPost(commentEvent.getPostId());

        // 글 작성자와 같다면 알림은 스킵
        if (Objects.equals(post.getUserId(), commentEvent.getUserId())) {
            return;
        }

        Comment comment = commentClient.getComment(commentEvent.getCommentId());

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
                comment.getId(),
                comment.getContent()
        );
    }

}
