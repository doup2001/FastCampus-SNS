package com.fc.service.dto;

import com.fc.domain.NotificationType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class LikeConvertedNotification extends ConvertedNotification {

    // 좋아요 알림은 하나에서 업데아트가 된다.
    private final String userName;
    private final String userProfileImageUrl;
    private final long userCount;
    private final String postImageUrl;


    public LikeConvertedNotification(String id, NotificationType type, Instant occurredAt, Instant lastUpdatedAt, String userName, String userProfileImageUrl, long userCount, String postImageUrl) {
        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userCount = userCount;
        this.postImageUrl = postImageUrl;
    }
}
