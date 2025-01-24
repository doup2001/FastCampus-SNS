package com.fc.response;

import com.fc.domain.NotificationType;
import com.fc.service.dto.FollowConvertedNotification;
import lombok.Getter;

import java.time.Instant;

@Getter
public class FollowUserNotificationResponse extends UserNotificationResponse {

    private final String userName;
    private final String userProfileImageUrl;
    private final boolean isFollowing;

    public FollowUserNotificationResponse(String id, NotificationType type, Instant occurredAt, String userName, String userProfileImageUrl, boolean isFollowing) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isFollowing = isFollowing;
    }

    public static FollowUserNotificationResponse of(FollowConvertedNotification notification) {
        return new FollowUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.isFollowing()
        );
    }
}
