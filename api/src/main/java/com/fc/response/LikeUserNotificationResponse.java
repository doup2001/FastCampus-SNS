package com.fc.response;

import com.fc.domain.NotificationType;
import com.fc.service.dto.LikeConvertedNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
@Schema(description = "좋아요 알림 응답")
public class LikeUserNotificationResponse extends UserNotificationResponse {

    @Schema(description = "좋아요한 사람의 이름")
    private final String userName;

    @Schema(description = "좋아요한 사람의 프로필 사진")
    private final String userProfileImageUrl;

    @Schema(description = "좋아요 누른 사람의 총 개수")
    private final long userCount;

    @Schema(description = "좋아요한 게시글의 사진")
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
