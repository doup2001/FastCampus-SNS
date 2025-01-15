package com.fc;

import java.time.Instant;

public class Notification {

    public String id;
    public Long userId;
    public NotificationType type;
    public Instant createdAt;
    public Instant deleteAt;

    public Notification(String id, Long userId, NotificationType type, Instant createdAt, Instant deleteAt) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.createdAt = createdAt;
        this.deleteAt = deleteAt;
    }

}
