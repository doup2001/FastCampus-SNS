package com.fc.response;

import com.fc.domain.NotificationType;
import com.fc.service.dto.CommentConvertedNotificaiton;
import lombok.Getter;

import java.time.Instant;

@Getter
public class CommentUserNotificationResponse extends UserNotificationResponse {

    private final String userName;
    private final String userProfileImageUrl;
    private final String comment;
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
