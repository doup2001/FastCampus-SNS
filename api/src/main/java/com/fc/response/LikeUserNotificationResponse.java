package com.fc.response;

import com.fc.domain.NotificationType;
import com.fc.service.dto.LikeConvertedNotification;
import lombok.Getter;

import java.time.Instant;

@Getter
public class LikeUserNotificationResponse extends UserNotificationResponse {

    private final String userName;
    private final String userProfileImageUrl;
    private final long userCount;
    private final String postImageUrl;

    public LikeUserNotificationResponse(String id, NotificationType type, Instant occurredAt, String userName, String userProfileImageUrl, long userCount, String postImageUrl) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userCount = userCount;
        this.postImageUrl = postImageUrl;
    }

    // 생성자
    public static LikeUserNotificationResponse of(LikeConvertedNotification notification) {
        return new LikeUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.getUserCount(),
                notification.getPostImageUrl()
        );
    }
}
