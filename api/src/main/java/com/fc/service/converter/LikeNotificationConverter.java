package com.fc.service.converter;

import com.fc.client.PostClient;
import com.fc.client.UserClient;
import com.fc.domain.LikeNotification;
import com.fc.domain.Post;
import com.fc.domain.User;
import com.fc.service.dto.LikeConvertedNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeNotificationConverter {

    private final UserClient userClient;
    private final PostClient postClient;

    public LikeConvertedNotification convert(LikeNotification notification) {
        Post post = postClient.getPost(notification.getPostId());
        User user = userClient.getUser(notification.getUserId());

        return new LikeConvertedNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getLikerIds().size(),
                post.getImageUrl()
        );
    }
}
