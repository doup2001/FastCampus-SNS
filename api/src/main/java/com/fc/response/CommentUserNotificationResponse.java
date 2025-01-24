package com.fc.response;

import com.fc.domain.NotificationType;
import com.fc.service.dto.CommentConvertedNotificaiton;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
@Schema(description = "댓글 알림 응답")
public class CommentUserNotificationResponse extends UserNotificationResponse {

    @Schema(description = "댓글을 남긴 사용자 이름")
    private final String userName;

    @Schema(description = "댓글을 남긴 사용자 프로필 사진")
    private final String userProfileImageUrl;

    @Schema(description = "댓글 내용")
    private final String comment;

    @Schema(description = "댓글을 남긴 게시물 사진")
    private final String postImageUrl;


    public CommentUserNotificationResponse(String id, NotificationType type, Instant occurredAt, String userName, String userProfileImageUrl, String comment, String postImageUrl) {

        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.comment = comment;
        this.postImageUrl = postImageUrl;
    }

    public static CommentUserNotificationResponse of(CommentConvertedNotificaiton notificaiton) {
        return new CommentUserNotificationResponse(
                notificaiton.getId(),
                notificaiton.getType(),
                notificaiton.getOccurredAt(),
                notificaiton.getUserName(),
                notificaiton.getUserProfileImageUrl(),
                notificaiton.getComment(),
                notificaiton.getPostImageUrl()
        );
    }

}
