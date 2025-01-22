package com.fc.service.converter;

import com.fc.client.UserClient;
import com.fc.domain.FollowNotification;
import com.fc.domain.User;
import com.fc.service.dto.FollowConvertedNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowNotificationConverter {

    private final UserClient userClient;

    public FollowConvertedNotification convert(FollowNotification notification) {
        User user = userClient.getUser(notification.getUserId());
        boolean isFollowing = userClient.isFollow(notification.getFollowerId(), notification.getUserId());

        return new FollowConvertedNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                isFollowing
        );
    }
}
