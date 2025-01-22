package com.fc.service;

import com.fc.domain.CommentNotification;
import com.fc.domain.FollowNotification;
import com.fc.domain.LikeNotification;
import com.fc.service.converter.CommentNotificationConverter;
import com.fc.service.converter.FollowNotificationConverter;
import com.fc.service.converter.LikeNotificationConverter;
import com.fc.service.dto.ConvertedNotification;
import com.fc.service.dto.GetUserNotificationsByPivotResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserNotificationService {

    private final NotificationListService listService;
    private final CommentNotificationConverter commentConverter;
    private final LikeNotificationConverter likeConverter;
    private final FollowNotificationConverter followConverter;

    // 들어온 값 변환
    public void getUserNotificationByPivot(long userId, Instant pivot) {

        GetUserNotificationsByPivotResult result = listService.getUserNotificationsByPivot(userId, pivot);

        List<ConvertedNotification> convertedNotifications = result.getList().stream()
                .map(notification -> switch (notification.getType()) {
                    case COMMENT -> commentConverter.convert((CommentNotification) notification);
                    case LIKE -> likeConverter.convert((LikeNotification) notification);
                    case FOLLOW -> followConverter.convert((FollowNotification) notification);
                })
                .toList();

    }


}
