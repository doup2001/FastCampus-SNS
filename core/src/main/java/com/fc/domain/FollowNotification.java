package com.fc.domain;

import lombok.Getter;

import java.time.Instant;

@Getter
public class FollowNotification extends Notification{

    // 필요한 변수 추가하기
    private final Long followerId; // 팔로우 하는 아이디 == 알림이 뜰 아이디

    public FollowNotification(String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdatedAt, Instant deleteAt, Long followerId) {
        super(id, userId, type, occurredAt, createdAt, lastUpdatedAt, deleteAt);
        this.followerId = followerId;
    }

}
