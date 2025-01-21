package com.fc.domain;

import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;

@Getter
@TypeAlias("CommentNotification")
public class CommentNotification extends Notification {

    private final Long postId;
    private final Long writerId;
    private final Long commentId;
    private final String comment;

    public CommentNotification(String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdatedAt, Instant deleteAt, Long postId, Long writerId, Long commentId, String comment) {
        super(id, userId, type, occurredAt, lastUpdatedAt, createdAt, deleteAt);
        this.postId = postId;
        this.writerId = writerId;
        this.commentId = commentId;
        this.comment = comment;
    }
}
