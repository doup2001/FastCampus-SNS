package com.fc.response;

import com.fc.domain.NotificationType;
import com.fc.service.dto.CommentConvertedNotificaiton;
import com.fc.service.dto.ConvertedNotification;
import com.fc.service.dto.FollowConvertedNotification;
import com.fc.service.dto.LikeConvertedNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class UserNotificationResponse {

    private String id;
    private NotificationType type;
    private Instant occurredAt;

    public static UserNotificationResponse of(ConvertedNotification notification) {

        switch (notification.getType()) {
            case LIKE -> {return LikeUserNotificationResponse.of((LikeConvertedNotification) notification);}
            case COMMENT -> {return CommentUserNotificationResponse.of((CommentConvertedNotificaiton) notification);}
            case FOLLOW -> {return FollowUserNotificationResponse.of((FollowConvertedNotification) notification);}
            default -> throw new IllegalArgumentException("예외가 발생했습니다"); // swith case의 return을 해주려면 default가 있어야한다.
        }
    }

}
