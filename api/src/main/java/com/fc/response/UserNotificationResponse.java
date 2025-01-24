package com.fc.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fc.domain.NotificationType;
import com.fc.service.dto.CommentConvertedNotificaiton;
import com.fc.service.dto.ConvertedNotification;
import com.fc.service.dto.FollowConvertedNotification;
import com.fc.service.dto.LikeConvertedNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Schema(description = "유저 알림 응답")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LikeUserNotificationResponse.class),
        @JsonSubTypes.Type(value = FollowUserNotificationResponse.class),
        @JsonSubTypes.Type(value = CommentUserNotificationResponse.class)
})

public abstract class UserNotificationResponse {

    @Schema(description = "알림 ID")
    private String id;

    @Schema(description = "알림 타입")
    private NotificationType type;

    @Schema(description = "알림 이벤트 발생 시간")
    private Instant occurredAt;

    public static UserNotificationResponse of(ConvertedNotification notification) {

        switch (notification.getType()) {
            case LIKE -> {
                return LikeUserNotificationResponse.of((LikeConvertedNotification) notification);
            }
            case COMMENT -> {
                return CommentUserNotificationResponse.of((CommentConvertedNotificaiton) notification);
            }
            case FOLLOW -> {
                return FollowUserNotificationResponse.of((FollowConvertedNotification) notification);
            }
            default -> throw new IllegalArgumentException("예외가 발생했습니다"); // swith case의 return을 해주려면 default가 있어야한다.
        }
    }

}
