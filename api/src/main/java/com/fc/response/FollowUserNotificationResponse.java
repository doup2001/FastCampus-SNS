package com.fc.response;

import com.fc.domain.NotificationType;
import com.fc.service.dto.FollowConvertedNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
@Schema(description = "팔로우 알림 응답")
public class FollowUserNotificationResponse extends UserNotificationResponse {

    @Schema(description = "팔로우한 사용자 이름")
    private final String userName;

    @Schema(description = "팔로우한 사용자 프로필 사진")
    private final String userProfileImageUrl;

    @Schema(description = "나의 팔로우 여부")
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
