package com.fc.domain.comment;

import com.fc.domain.notificaition.Notification;
import com.fc.domain.notificaition.NotificationType;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;

@Getter
@TypeAlias("CommentNotification")
public class CommentNotification extends Notification {

    private final Long postId;
    private final Long writerId;
    private final String comment;

    public CommentNotification(String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdateAdt, Instant deleteAt, Long postId, Long writerId, String comment) {
        super(id, userId, type, occurredAt, lastUpdateAdt, createdAt, deleteAt);
        this.postId = postId;
        this.writerId = writerId;
        this.comment = comment;
    }
}
