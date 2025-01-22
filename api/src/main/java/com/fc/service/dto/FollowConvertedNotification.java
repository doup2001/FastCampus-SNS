package com.fc.service.dto;

import com.fc.domain.NotificationType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class FollowConvertedNotification extends ConvertedNotification {

    private final String userName;
    private final String userProfileImageUrl;
    private final boolean isFollowing;

    public FollowConvertedNotification(String id, NotificationType type, Instant occurredAt, Instant lastUpdatedAt, String userName, String userProfileImageUrl, boolean isFollowing) {
        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isFollowing = isFollowing;
    }
}
