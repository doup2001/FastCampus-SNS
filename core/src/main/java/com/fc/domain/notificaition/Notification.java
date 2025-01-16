package com.fc.domain.notificaition;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("notifications")
public abstract class Notification {

    public String id;
    public Long userId;
    public NotificationType type;
    public Instant occurredAt;
    public Instant createdAt;
    public Instant lastUpdatedAt;
    public Instant deleteAt;

    public Notification(String id, Long userId, NotificationType type,Instant occurredAt, Instant createdAt, Instant lastUpdatedAt, Instant deleteAt) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.occurredAt = occurredAt;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.deleteAt = deleteAt;
    }

}
