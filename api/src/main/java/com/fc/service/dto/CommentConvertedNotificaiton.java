package com.fc.service.dto;

import com.fc.domain.NotificationType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class CommentConvertedNotificaiton extends ConvertedNotification{

    private final String userName;
    private final String userProfileImageUrl;
    private final String comment;
    private final String postImageUrl;

    public CommentConvertedNotificaiton(String id, NotificationType type, Instant occurredAt, Instant lastUpdatedAt, String userName,String userProfileImageUrl, String comment, String postImageUrl) {
        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.comment = comment;
        this.postImageUrl = postImageUrl;
    }
}
